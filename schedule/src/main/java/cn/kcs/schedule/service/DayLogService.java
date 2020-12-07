package cn.kcs.schedule.service;

import cn.kcs.common.util.PageRequest;
import cn.kcs.common.util.ResponseDto;
import cn.kcs.schedule.entity.DayLog;

import java.util.List;

/**
 * (DayLog)表服务接口
 *
 * @author makejava
 * @since 2020-12-07 15:42:31
 */
public interface DayLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DayLog queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<DayLog> queryAllByLimit(DayLog dayLog, int offset, int limit);

    /**
     * 新增数据
     *
     * @param dayLog 实例对象
     * @return 实例对象
     */
    boolean insert(DayLog dayLog);

    /**
     * 修改数据
     *
     * @param dayLog 实例对象
     * @return 实例对象
     */
    boolean update(DayLog dayLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    ResponseDto queryPager(DayLog dayLog, PageRequest pageRequest);
}