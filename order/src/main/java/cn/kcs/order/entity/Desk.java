package cn.kcs.order.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Desk)实体类
 *
 * @author kcs
 * @since 2019-04-24 14:35:14
 */
public class Desk implements Serializable {
    private static final long serialVersionUID = 915276019148120117L;

    private String deskId;

    private String deskName;

    private String deskStatus;

    private String deskSerialNumber;

    private String deskCapacity;

    private Date deskCreateTime;


    public String getDeskId() {
        return deskId;
    }

    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

    public String getDeskName() {
        return deskName;
    }

    public void setDeskName(String deskName) {
        this.deskName = deskName;
    }

    public String getDeskStatus() {
        return deskStatus;
    }

    public void setDeskStatus(String deskStatus) {
        this.deskStatus = deskStatus;
    }

    public String getDeskSerialNumber() {
        return deskSerialNumber;
    }

    public void setDeskSerialNumber(String deskSerialNumber) {
        this.deskSerialNumber = deskSerialNumber;
    }

    public String getDeskCapacity() {
        return deskCapacity;
    }

    public void setDeskCapacity(String deskCapacity) {
        this.deskCapacity = deskCapacity;
    }

    public Date getDeskCreateTime() {
        return deskCreateTime;
    }

    public void setDeskCreateTime(Date deskCreateTime) {
        this.deskCreateTime = deskCreateTime;
    }

}