package cn.kcs.user.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.user.entity.LoggerDto;

import java.util.List;

/**
 * (Logger)表服务接口
 *
 * @author kcs
 * @date 2019-09-06 13:05:53
 */
public interface LoggerService {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    LoggerDto queryById(String logId);

    /**
     * 查询多条数据
     *
     * @param loggerDto logger
     * @param offset    查询起始位置
     * @param limit     查询条数
     * @return 对象列表
     */
    List<LoggerDto> queryAllByLimit(LoggerDto loggerDto, PageRequest pageRequest, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @param loggerDto logger
     * @return 对象列表
     */
    List<LoggerDto> queryAll(LoggerDto loggerDto);

    /**
     * 新增数据
     *
     * @param loggerDto 实例对象
     * @return 实例对象
     */
    boolean insert(LoggerDto loggerDto);

    /**
     * 修改数据
     *
     * @param loggerDto 实例对象
     * @return 实例对象
     */
    LoggerDto update(LoggerDto loggerDto);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    boolean deleteById(String logId);

}