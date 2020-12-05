package cn.kcs.user.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.UserAccount;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * (UserAccount)表服务接口
 *
 * @author kcs
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
     * @param account 查询起始位置
     * @param pageRequest  查询条数
     * @return 对象列表
     */
    List<UserAccount> queryAllByLimit(UserAccount account, PageRequest pageRequest);

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
    ResponseEntity registerAccount(UserAccount account);
}