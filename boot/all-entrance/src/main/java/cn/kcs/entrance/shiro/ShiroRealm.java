package cn.kcs.entrance.shiro;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CommonUtil;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.constants.Constants;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.dto.UserInfo;
import cn.kcs.user.service.UserAccountService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author kcs
 * @date 2018-10-26 11:25
 **/
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserAccountService userAccountService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //查询用户的权限
        UserAccount userAccount = userAccountService.queryById(LoginInfo.getUserId());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (userAccount != null && !StringUtils.isBlank(userAccount.getUserRole())) {
            String roleName = userAccount.getUserRoleName();
            List<String> permissionList = userAccount.getPermissionList();
            logger.info("user{}role为:{}", userAccount.getUserName(), roleName);
            authorizationInfo.addRole(roleName);
            authorizationInfo.addStringPermissions(permissionList);
        }
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) {
        // 获取用户密码
        String loginName = (String) authToken.getPrincipal();
        String password = new String((char[]) authToken.getCredentials());
        UserAccount userAccount = new UserAccount();
        if (CommonUtil.isEmail(loginName)) {
            userAccount.setUserEmail(loginName);
        } else if (CommonUtil.isPhone(loginName)) {
            userAccount.setUserPhone(loginName);
        } else {
            userAccount.setUserName(loginName);
        }
        List<UserAccount> users = userAccountService.queryAll(userAccount);
        if (CollectionUtils.isEmpty(users)) {
            logger.info("用户名错误");
            throw new UnknownAccountException("用户名错误", new Throwable(loginName));
        }
        userAccount.setUserPassword(password);
        List<UserAccount> userAccounts = userAccountService.queryAll(userAccount);
        if (CollectionUtils.isEmpty(userAccounts)) {
            logger.info("[{}]用户密码错误", loginName);
            throw new UnknownAccountException("密码错误", new Throwable(password));
        } else {
            UserAccount account = userAccountService.queryById(userAccounts.get(0).getUserId());
            boolean flag = "0".equals(account.getUserStatus());
            if (flag) {
                logger.info("[{}]该用户已被禁用", loginName);
                throw new DisabledAccountException("该用户已被禁用");
            }
            account.setUserLoginNumber(Integer.toString(Integer.parseInt(account.getUserLoginNumber()) + 1));
            account.setUserLastLoginTime(CustomDateUtil.currentFormatDate());
            userAccountService.update(account);
            List<String> permissionList = account.getPermissionList();
            if (CollectionUtils.isEmpty(permissionList)) {
                permissionList = new ArrayList<>();
            }
            //session中需要保存的信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(account.getUserId());
            userInfo.setUserName(account.getUserName());
            userInfo.setUserPhone(account.getUserPhone());
            userInfo.setUserEmail(account.getUserEmail());
            userInfo.setUserAvatar(account.getUserAvatar());
            userInfo.setRoleName(account.getUserRoleName());
            userInfo.setPermissions(permissionList);
            JSONObject jsonUser = JSONObject.parseObject(JSONObject.toJSON(userInfo).toString());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    account.getUserName(),
                    account.getUserPassword(),
                    ByteSource.Util.bytes(account.getUserName() + "salt"),
                    getName()
            );
            //将用户信息放入session中
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, jsonUser);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_PERMISSION, permissionList);
            return authenticationInfo;
        }
    }
}
