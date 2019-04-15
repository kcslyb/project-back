package cn.kcs.note.controller.shiro;

import cn.kcs.common.util.constants.Constants;
import cn.kcs.note.entity.TPermissionMenu;
import cn.kcs.note.entity.UserAccount;
import cn.kcs.note.service.TRoleService;
import cn.kcs.note.service.UserAccountService;
import cn.kcs.service.inter.dto.PermissionDto;
import cn.kcs.service.inter.dto.RoleDto;
import cn.kcs.service.inter.dto.UserInfo;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-26 11:25
 **/
public class ShiroRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TRoleService tRoleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        RoleDto roleDto = (RoleDto) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (roleDto != null) {
            logger.info("role的值为:" + roleDto.getRoleName());
            authorizationInfo.addRole(roleDto.getRoleName());
            logger.info("permission权限为:" + roleDto.getPermissionDtos());
            List<PermissionDto> permissionDtos = roleDto.getPermissionDtos();
            for (PermissionDto permission : permissionDtos) {
                authorizationInfo.addStringPermission(permission.getPermissionUrl());
            }
        }
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        // 获取用户密码
        String loginName = (String) authcToken.getPrincipal();
        String password = new String((char[]) authcToken.getCredentials());
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(loginName);
        userAccount.setUserPassword(password);
        List<UserAccount> userAccounts = userAccountService.queryAll(userAccount);
        if (userAccounts.size() > 0) {
            JSONObject user = JSONObject.parseObject(JSONObject.toJSON(userAccounts.get(0)).toString());
            UserAccount account = userAccountService.queryById(userAccounts.get(0).getUserId());
            //设置menu和permission
            List<String> menu = new ArrayList<>();
            List<String> permission = new ArrayList<>();
            if (account.getUserPermission().size() > 0) {
                for (TPermissionMenu perm : account.getUserPermission()) {
                    if (perm != null) {
                        menu.add(perm.getPermpath());
                        permission.add(perm.getPermvalue());
                    }
                }
            }
            //session中需要保存的信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(account.getUserId());
            userInfo.setUserName(account.getUserName());
            userInfo.setUserPhone(account.getUserPhone());
            userInfo.setUserEmail(account.getUserEmail());
            userInfo.setUserAvatar(account.getUserAvatar());
            userInfo.setRoleName(tRoleService.queryById(account.getUserRole()).getRoleName());
            userInfo.setMenus(menu);
            userInfo.setPermissions(permission);
            JSONObject jsonUser = JSONObject.parseObject(JSONObject.toJSON(userInfo).toString());
//            JSONObject userRole = JSONObject.parseObject(JSONObject.toJSON(tRoleService.queryById(account.getUserRole()).getRoleName()).toString());
            if (account == null) {
                //没找到帐号
                logger.info("没有该用户");
                throw new UnknownAccountException();
            }
            if ("0".equals(account.getUserStatus())) {
                //禁用的账号
                logger.info("该用户已被禁用");
                throw new DisabledAccountException();
            }
            //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    account.getUserName(),
                    account.getUserPassword(),
                    ByteSource.Util.bytes(account.getUserName() + "salt"), //salt=username+salt,采用明文访问时，不需要此句
                    getName()
            );
            //将用户信息放入session中
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, jsonUser);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_PERMISSION, permission);
            return authenticationInfo;
        }
        return null;
    }
}
