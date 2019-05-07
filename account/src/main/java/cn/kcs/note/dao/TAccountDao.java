package cn.kcs.note.dao;

import cn.kcs.note.entity.TAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TAccount)表数据库访问层
 *
 * @author makejava
 * @since 2019-02-25 09:39:21
 */
public interface TAccountDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id
     * @return 实例对象
     */
    TAccount queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TAccount> queryAllByLimit(@Param("account") TAccount tAccount, @Param("offset") int offset, @Param("limit") int limit, @Param("startTime") String starTime, @Param("endTime") String endTime);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tAccount 实例对象
     * @return 对象列表
     */
    List<TAccount> queryAll(TAccount tAccount);

    /**
     * 新增数据
     *
     * @param tAccount 实例对象
     * @return 影响行数
     */
    int insert(TAccount tAccount);

    /**
     * 修改数据
     *
     * @param tAccount 实例对象
     * @return 影响行数
     */
    int update(TAccount tAccount);

    /**
     * 通过主键删除数据
     *
     * @param id
     * @return 影响行数
     */
    int deleteById(String id);

}