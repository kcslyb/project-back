package cn.kcs.schedule.dao;

import cn.kcs.schedule.entity.Schedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Schedule)表数据库访问层
 *
 * @author kcs
 * @date 2019-09-05 12:50:36
 */
public interface ScheduleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param scheduleId 主键
     * @return 实例对象
     */
    Schedule queryById(@Param("scheduleId") String scheduleId);

    /**
     * 查询指定行数据
     *
     * @param schedule 实例对象
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    List<Schedule> queryAllByLimit(@Param("schedule")Schedule schedule, @Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param schedule 实例对象
     * @return 对象列表
     */
    List<Schedule> queryAll(Schedule schedule);

    /**
     * 新增数据
     *
     * @param schedule 实例对象
     * @return 影响行数
     */
    int insert(Schedule schedule);

    /**
     * 修改数据
     *
     * @param schedule 实例对象
     * @return 影响行数
     */
    int update(Schedule schedule);

    /**
     * 通过主键删除数据
     *
     * @param scheduleId 主键
     * @return 影响行数
     */
    int deleteById(@Param("scheduleId") String scheduleId);

    /**
     * 通过时间段查询数据
     *
     * @param startTime startTime
     * @param endTime   endTime
     * @return 数据
     */
    List<Schedule> queryPeriodOfTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 通过时间查询数据
     *
     * @param time time
     * @return 数据
     */
    List<Schedule> queryOfTime(@Param("time") String time);
}