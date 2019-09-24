package cn.kcs.schedule.service;

import cn.kcs.schedule.entity.Schedule;

import java.util.List;

/**
 * (Schedule)表服务接口
 *
 * @author kcs
 * @date 2019-09-05 12:50:36
 */
public interface ScheduleService {

    /**
     * 通过ID查询单条数据
     *
     * @param scheduleId 主键
     * @return 实例对象
     */
    Schedule queryById(String scheduleId);

    /**
     * 查询多条数据
     *
     * @param schedule schedule
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<Schedule> queryAllByLimit(Schedule schedule, int offset, int limit);

    /**
     * 新增数据
     *
     * @param schedule 实例对象
     * @return 实例对象
     */
    Schedule insert(Schedule schedule);

    /**
     * 修改数据
     *
     * @param schedule 实例对象
     * @return 实例对象
     */
    Schedule update(Schedule schedule);

    /**
     * 通过主键删除数据
     *
     * @param scheduleId 主键
     * @return 是否成功
     */
    boolean deleteById(String scheduleId);

    /**
     * 通过时间段查询数据
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @return 数据
     */
    List<Schedule> queryPeriodOfTime(String startTime, String endTime);

    /**
     * 通过时间查询数据
     *
     * @param time time
     * @return 数据
     */
    List<Schedule> queryOfTime(String time);
}