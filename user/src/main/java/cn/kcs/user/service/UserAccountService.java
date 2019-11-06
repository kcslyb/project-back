package cn.kcs.user.service;

import cn.kcs.user.entity.UserAccount;

import java.util.List;

/**
 * (UserAccount)表服务接口
 *
 * @author makejava
 * @since 2019-03-21 14:46:59
 */
public interface UserAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserAccount queryById(String userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserAccount> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param userAccount 实例对象
     * @return 对象列表
     */
    List<UserAccount> queryAll(UserAccount userAccount);

    /**
     * 新增数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    boolean insert(UserAccount userAccount);

    /**
     * 修改数据
     *
     * @param userAccount 实例对象
     * @return 实例对象
     */
    UserAccount update(UserAccount userAccount);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(String userId);

    /**
     * 注册
     *
     * @param account 实体
     * @return 是否成功
     */
    boolean signInAccount(UserAccount account);
}