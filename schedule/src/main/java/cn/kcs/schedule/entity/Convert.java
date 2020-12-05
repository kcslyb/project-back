package cn.kcs.schedule.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Convert)实体类
 *
 * @author kcs
 * @since 2020-09-17 20:11:25
 */
public class Convert implements Serializable {
    private static final long serialVersionUID = 418589790460938521L;

    private String id;

    private String createBy;

    private String createByName;

    private Date createTime;

    private String updateBy;

    private String updateByName;

    private Date updateTime;

    private Double oneTypeNumber;

    private String oneTypeUnit;

    private String oneTypeUnitName;

    private Double twoTypeNumber;

    private String twoTypeUnit;

    private String twoTypeUnitName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public Double getOneTypeNumber() {
        return oneTypeNumber;
    }

    public void setOneTypeNumber(Double oneTypeNumber) {
        this.oneTypeNumber = oneTypeNumber;
    }

    public String getOneTypeUnit() {
        return oneTypeUnit;
    }

    public void setOneTypeUnit(String oneTypeUnit) {
        this.oneTypeUnit = oneTypeUnit;
    }

    public String getOneTypeUnitName() {
        return oneTypeUnitName;
    }

    public void setOneTypeUnitName(String oneTypeUnitName) {
        this.oneTypeUnitName = oneTypeUnitName;
    }

    public Double getTwoTypeNumber() {
        return twoTypeNumber;
    }

    public void setTwoTypeNumber(Double twoTypeNumber) {
        this.twoTypeNumber = twoTypeNumber;
    }

    public String getTwoTypeUnit() {
        return twoTypeUnit;
    }

    public void setTwoTypeUnit(String twoTypeUnit) {
        this.twoTypeUnit = twoTypeUnit;
    }

    public String getTwoTypeUnitName() {
        return twoTypeUnitName;
    }

    public void setTwoTypeUnitName(String twoTypeUnitName) {
        this.twoTypeUnitName = twoTypeUnitName;
    }

}