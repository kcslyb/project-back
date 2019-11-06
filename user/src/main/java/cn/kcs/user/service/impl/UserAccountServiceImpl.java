package cn.kcs.user.service.impl;

import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.Md5Utils;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.entity.RolePermission;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.dto.RoleDto;
import cn.kcs.user.service.RoleService;
import cn.kcs.user.service.UserAccountService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (UserAccount)表服务实现类
 *
 * @author makejava
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
        return userAccount;
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserAccount> queryAllByLimit(int offset, int limit) {
        return this.userAccountDao.queryAllByLimit(offset, limit);
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
            RoleDto roleDto = roleService.queryById(account.getUserRole());
            account.setUserRoleName(roleDto.getRoleName());
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
        userAccount.setUserId(ShortUUID.generate());
        userAccount.setUserCreateTime(CustomDateUtil.stringToDate(CustomDateUtil.currentFormatDate(CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN), CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN));
        userAccount.setUserUpdateTime(userAccount.getUserCreateTime());
        userAccount.setUserLastLoginTime(userAccount.getUserCreateTime());
        userAccount.setUserLoginNumber("0");
        if (userAccount.getUserRole() == null || "".equals(userAccount.getUserRole())) {
            userAccount.setUserRole("IHVHwXpHl2cmppU13Qx");
        }
        if (userAccount.getUserAvatar() == null || "".equals(userAccount.getUserAvatar())) {
            userAccount.setUserAvatar("http://127.0.0.1:8018/static/imagejpeg/35569e03a92b836b621152f2ba311c6f.jpg");
        }
        if (userAccount.getUserStatus() == null || "".equals(userAccount.getUserStatus())) {
            userAccount.setUserStatus("1");
        }
        if (userAccount.getUserPassword() == null || "".equals(userAccount.getUserPassword())) {
            userAccount.setUserPassword(Md5Utils.GetMD5Code(Md5Utils.GetMD5Code("123456")));
        } else {
            userAccount.setUserPassword(Md5Utils.GetMD5Code(Md5Utils.GetMD5Code(userAccount.getUserPassword())));
        }
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
        userAccount.setUserUpdateTime(CustomDateUtil.stringToDate(CustomDateUtil.currentFormatDate(CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN), CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN));
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
    public boolean signInAccount(UserAccount account) {
        return insert(account);
    }
}