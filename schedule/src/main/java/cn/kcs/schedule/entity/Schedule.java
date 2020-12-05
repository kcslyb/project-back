package cn.kcs.schedule.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Schedule)实体类
 *
 * @author kcs
 * @since 2019-09-05 12:50:33
 */
public class Schedule implements Serializable {
    private static final long serialVersionUID = -63642119266656821L;

    private String scheduleId;

    private String scheduleTitle;

    private String scheduleCreateBy;

    private Date scheduleCreateTime;

    private String scheduleStatus;

    private String scheduleRemake;


    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleTitle() {
        return scheduleTitle;
    }

    public void setScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }

    public String getScheduleCreateBy() {
        return scheduleCreateBy;
    }

    public void setScheduleCreateBy(String scheduleCreateBy) {
        this.scheduleCreateBy = scheduleCreateBy;
    }

    public Date getScheduleCreateTime() {
        return scheduleCreateTime;
    }

    public void setScheduleCreateTime(Date scheduleCreateTime) {
        this.scheduleCreateTime = scheduleCreateTime;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    public String getScheduleRemake() {
        return scheduleRemake;
    }

    public void setScheduleRemake(String scheduleRemake) {
        this.scheduleRemake = scheduleRemake;
    }

}