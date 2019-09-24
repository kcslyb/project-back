package cn.kcs.schedule.service.impl;

import cn.kcs.common.logininfo.LoginInfo;
import cn.kcs.common.util.CustomDateUtil;
import cn.kcs.common.uuidutil.ShortUUID;
import cn.kcs.schedule.dao.ScheduleDao;
import cn.kcs.schedule.entity.Schedule;
import cn.kcs.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Schedule)表服务实现类
 *
 * @author makejava
 * @since 2019-09-05 12:50:37
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
    @Resource
    private ScheduleDao scheduleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param scheduleId 主键
     * @return 实例对象
     */
    @Override
    public Schedule queryById(String scheduleId) {
        return this.scheduleDao.queryById(scheduleId);
    }

    /**
     * 查询多条数据
     *
     * @param schedule schedule
     * @param offset   查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public List<Schedule> queryAllByLimit(Schedule schedule, int offset, int limit) {
        return this.scheduleDao.queryAllByLimit(schedule, offset, limit);
    }

    /**
     * 新增数据
     *
     * @param schedule 实例对象
     * @return 实例对象
     */
    @Override
    public Schedule insert(Schedule schedule) {
        schedule.setScheduleId(ShortUUID.generate());
        schedule.setScheduleCreateBy(LoginInfo.getUserName());
        schedule.setScheduleStatus("0");
        schedule.setScheduleCreateTime(CustomDateUtil.currentFormatDate());
        this.scheduleDao.insert(schedule);
        return schedule;
    }

    /**
     * 修改数据
     *
     * @param schedule 实例对象
     * @return 实例对象
     */
    @Override
    public Schedule update(Schedule schedule) {
        this.scheduleDao.update(schedule);
        return this.queryById(schedule.getScheduleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param scheduleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String scheduleId) {
        return this.scheduleDao.deleteById(scheduleId) > 0;
    }

    @Override
    public List<Schedule> queryPeriodOfTime(String startTime, String endTime) {
        return this.scheduleDao.queryPeriodOfTime(startTime, endTime);
    }

    @Override
    public List<Schedule> queryOfTime(String time) {
        return this.scheduleDao.queryOfTime(time);
    }
}