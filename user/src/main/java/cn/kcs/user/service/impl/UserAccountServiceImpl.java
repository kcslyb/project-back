package cn.kcs.user.service.impl;

import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.util.Md5Utils;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.user.dao.TPermissionMenuDao;
import cn.kcs.user.dao.UserAccountDao;
import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.service.UserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private TPermissionMenuDao tPermissionMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public UserAccount queryById(String userId) {
        UserAccount userAccount = this.userAccountDao.queryById(userId);
        userAccount.setUserPermission(tPermissionMenuDao.queryAllPermissionByRole(userAccount.getUserRole()));
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
        return this.userAccountDao.queryAll(userAccount);
    }

    /**
     * 新增数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    @Override
    public UserAccount insert(UserAccount userAccount) {
        userAccount.setUserId(ShortUUID.generate());
        userAccount.setUserCreateTime(CustomDateUtil.stringToDate(CustomDateUtil.currentFormatDate(CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN), CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN));
        userAccount.setUserUpdateTime(userAccount.getUserCreateTime());
        userAccount.setUserLastLoginTime(userAccount.getUserCreateTime());
        userAccount.setUserLoginNumber("0");
        if (userAccount.getUserRole() == null || "".equals(userAccount.getUserRole())) {
            userAccount.setUserRole("IHVHwXpHl2cmppU13Qx");
        }
        if (userAccount.getUserAvatar() == null || "".equals(userAccount.getUserAvatar())) {
            userAccount.setUserAvatar("http://127.0.0.1:8018/static/777.jpg");
        }
        if (userAccount.getUserStatus() == null || "".equals(userAccount.getUserStatus())) {
            userAccount.setUserStatus("1");
        }
        if (userAccount.getUserPassword() == null || "".equals(userAccount.getUserPassword())) {
            userAccount.setUserPassword(Md5Utils.GetMD5Code(Md5Utils.GetMD5Code("123456")));
        } else {
            userAccount.setUserPassword(Md5Utils.GetMD5Code(Md5Utils.GetMD5Code(userAccount.getUserPassword())));
        }
        this.userAccountDao.insert(userAccount);
        return userAccount;
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
}