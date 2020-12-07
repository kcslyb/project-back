package cn.kcs.schedule.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DayLog)实体类
 *
 * @author makejava
 * @since 2020-12-07 15:42:30
 */
public class DayLog implements Serializable {
    private static final long serialVersionUID = 282271478065236264L;
    /**
     * 主键
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 创建者id
     */
    private String createById;
    /**
     * 创建者名称
     */
    private String createByName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 发生时间
     */
    private Date happenTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标准
     */
    private String deleteFlag;
    /**
     * 修改人id
     */
    private String updateById;
    /**
     * 修改人名称
     */
    private String updateByName;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 预留字段一
     */
    private String reservedKeyOne;
    /**
     * 预留字段二
     */
    private String reservedKeyTwo;
    /**
     * 开始时间（用于查询）
     */
    private String startTime;
    /**
     * 结束时间（用于查询）
     */
    private String endTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateById() {
        return createById;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getUpdateById() {
        return updateById;
    }

    public void setUpdateById(String updateById) {
        this.updateById = updateById;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReservedKeyOne() {
        return reservedKeyOne;
    }

    public void setReservedKeyOne(String reservedKeyOne) {
        this.reservedKeyOne = reservedKeyOne;
    }

    public String getReservedKeyTwo() {
        return reservedKeyTwo;
    }

    public void setReservedKeyTwo(String reservedKeyTwo) {
        this.reservedKeyTwo = reservedKeyTwo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}