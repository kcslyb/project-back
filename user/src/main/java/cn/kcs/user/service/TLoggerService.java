package cn.kcs.user.service;

import cn.kcs.user.entity.TLogger;

import java.util.List;

/**
 * (TLogger)表服务接口
 *
 * @author makejava
 * @since 2019-04-17 11:46:47
 */
public interface TLoggerService {

    /**
     * 通过ID查询单条数据
     *
     * @param logId 主键
     * @return 实例对象
     */
    TLogger queryById(String logId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<TLogger> queryAllByLimit(TLogger tLogger, int offset, int limit);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<TLogger> queryAll(TLogger tLogger);

    /**
     * 新增数据
     *
     * @param tLogger 实例对象
     * @return 实例对象
     */
    TLogger insert(TLogger tLogger);

    /**
     * 修改数据
     *
     * @param tLogger 实例对象
     * @return 实例对象
     */
    TLogger update(TLogger tLogger);

    /**
     * 通过主键删除数据
     *
     * @param logId 主键
     * @return 是否成功
     */
    boolean deleteById(String logId);

}