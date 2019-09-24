package cn.kcs.user.dao;

import cn.kcs.user.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (UserAccount)表数据库访问层
 *
 * @author makejava
 * @since 2019-03-21 14:46:59
 */
public interface UserAccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserAccount queryById(@Param("userId") String userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<UserAccount> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


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
     * @return 影响行数
     */
    int insert(UserAccount userAccount);

    /**
     * 修改数据
     *
     * @param userAccount 实例对象
     * @return 影响行数
     */
    int update(UserAccount userAccount);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(@Param("userId") String userId);

}