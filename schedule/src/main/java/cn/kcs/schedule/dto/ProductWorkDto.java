package cn.kcs.schedule.dto;

import cn.kcs.schedule.entity.ConvertProduct;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (ProductWork)实体类
 *
 * @author kcs
 * @since 2020-09-17 20:16:24
 */
public class ProductWorkDto implements Serializable {
    private static final long serialVersionUID = 114988391150404866L;

    private String id;

    private String createBy;

    private String createByName;

    private Date createTime;

    private Date happenTime;

    private String updateBy;

    private String updateByName;

    private Date updateTime;

    private Double resultNumber;

    private String resultUnit;

    private String resultUnitName;

    private Double minNumber;

    private String minUnit;

    private String minUnitName;

    private Boolean deleteFlag;

    private String participants;

    private Integer participantsNumber;

    private String remark;

    private double average;

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public Integer getParticipantsNumber() {
        return participantsNumber;
    }

    public void setParticipantsNumber(Integer participantsNumber) {
        this.participantsNumber = participantsNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public List<ConvertProduct> getConvertProducts() {
        return convertProducts;
    }

    public void setConvertProducts(List<ConvertProduct> convertProducts) {
        this.convertProducts = convertProducts;
    }

    private List<ConvertProduct> convertProducts;

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

    public Date getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(Date happenTime) {
        this.happenTime = happenTime;
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

    public Double getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(Double resultNumber) {
        this.resultNumber = resultNumber;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    public String getResultUnitName() {
        return resultUnitName;
    }

    public void setResultUnitName(String resultUnitName) {
        this.resultUnitName = resultUnitName;
    }

    public Double getMinNumber() {
        return minNumber;
    }

    public void setMinNumber(Double minNumber) {
        this.minNumber = minNumber;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getMinUnitName() {
        return minUnitName;
    }

    public void setMinUnitName(String minUnitName) {
        this.minUnitName = minUnitName;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}