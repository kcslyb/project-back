package cn.kcs.user.dao;

import cn.kcs.user.entity.LoggerDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Logger)表数据库访问层
 *
 * @author kcs
 * @date 2019-09-06 13:05:53
 */
public interface LoggerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    LoggerDto queryById(@Param("logId") String logId);

    /**
     * 查询指定行数据
     *
     * @param loggerDto logger
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    List<LoggerDto> queryAllByLimit(@Param("loggerDto") LoggerDto loggerDto, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param loggerDto 实例对象
     * @return 对象列表
     */
    List<LoggerDto> queryAll(LoggerDto loggerDto);

    /**
     * 新增数据
     *
     * @param loggerDto 实例对象
     * @return 影响行数
     */
    int insert(LoggerDto loggerDto);

    /**
     * 修改数据
     *
     * @param loggerDto 实例对象
     * @return 影响行数
     */
    int update(LoggerDto loggerDto);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 影响行数
     */
    int deleteById(@Param("logId") String logId);

}