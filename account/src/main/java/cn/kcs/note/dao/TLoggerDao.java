package cn.kcs.note.dao;

import cn.kcs.note.entity.TLogger;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (TLogger)表数据库访问层
 *
 * @author makejava
 * @since 2019-04-17 11:46:47
 */
public interface TLoggerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    TLogger queryById(@Param("logId") String logId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TLogger> queryAllByLimit(@Param("logger") TLogger tLogger, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tLogger 实例对象
     * @return 对象列表
     */
    List<TLogger> queryAll(TLogger tLogger);

    /**
     * 新增数据
     *
     * @param tLogger 实例对象
     * @return 影响行数
     */
    int insert(TLogger tLogger);

    /**
     * 修改数据
     *
     * @param tLogger 实例对象
     * @return 影响行数
     */
    int update(TLogger tLogger);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 影响行数
     */
    int deleteById(String logId);

}