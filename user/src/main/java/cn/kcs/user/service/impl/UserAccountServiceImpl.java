package cn.kcs.user.service.impl;

import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.Md5Utils;
import cn.kcs.common.util.PageRequest;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.entity.RolePermission;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.dto.RoleDto;
import cn.kcs.user.service.RoleService;
import cn.kcs.user.service.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (UserAccount)表服务实现类
 *
 * @author kcs
 * @since 2019-03-21 14:46:59
 */
@Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {
    @Resource
    private UserAccountDao userAccountDao;

    @Resource
    private RoleService roleService;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public UserAccount queryById(String userId) {
        UserAccount userAccount = this.userAccountDao.queryById(userId);
        if (!StringUtils.isBlank(userAccount.getUserRole())) {
            RoleDto roleDto = roleService.queryById(userAccount.getUserRole());
            userAccount.setUserRoleName(roleDto.getRoleName());
            List<RolePermission> rolePermissionList = roleDto.getRolePermissionList();
            if (CollectionUtils.isEmpty(rolePermissionList)) {
                return userAccount;
            }
            List<String> permissionList = new ArrayList<>();
            rolePermissionList.forEach(value -> {
                permissionList.add(value.getRolePermissionLabel());
            });
            userAccount.setPermissionList(permissionList);
        }
        return userAccount;
    }

    /**
     * 查询多条数据
     *
     * @param account 查询起始位置
     * @param pageRequest  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserAccount> queryAllByLimit(UserAccount account, PageRequest pageRequest) {
        return this.userAccountDao.queryAllByLimit(account, pageRequest);
    }

    /**
     * 查询多条数据
     *
     * @param userAccount
     * @return 对象列表
     */
    @Override
    public List<UserAccount> queryAll(UserAccount userAccount) {
        List<UserAccount> userAccounts = this.userAccountDao.queryAll(userAccount);
        for (UserAccount account : userAccounts) {
            if (!StringUtils.isBlank(userAccount.getUserRole())) {
                RoleDto roleDto = roleService.queryById(account.getUserRole());
                account.setUserRoleName(roleDto.getRoleName());
            }
        }
        return userAccounts;
    }

    /**
     * 新增数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(UserAccount userAccount) {
        userAccount.setUserPassword(Md5Utils.GetMD5Code(Md5Utils.GetMD5Code(userAccount.getUserPassword())));
        List<UserAccount> userAccounts = queryAll(userAccount);
        if (!CollectionUtils.isEmpty(userAccounts)) {
            return false;
        }
        userAccount.setUserId(ShortUUID.generate());
        userAccount.setUserStatus("1");
        userAccount.setUserLoginNumber("0");
        userAccount.setUserCreateTime(CustomDateUtil.currentFormatDate());
        return this.userAccountDao.insert(userAccount) > 0;
    }

    /**
     * 修改数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    @Override
    public UserAccount update(UserAccount userAccount) {
        userAccount.setUserUpdateTime(CustomDateUtil.currentFormatDate());
        this.userAccountDao.update(userAccount);
        return this.queryById(userAccount.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userId) {
        return this.userAccountDao.deleteById(userId) > 0;
    }

    /**
     * 注册
     *
     * @param account 实体
     * @return 是否成功
     */
    @Override
    public ResponseEntity registerAccount(UserAccount account) {
        boolean insert = insert(account);
        if (!insert) {
            return new ResponseEntity<>("该账户已经存在", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}