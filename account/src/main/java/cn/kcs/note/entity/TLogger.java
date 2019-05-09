package cn.kcs.note.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TLogger)实体类
 *
 * @author makejava
 * @since 2019-04-17 11:46:47
 */
public class TLogger implements Serializable {
    private static final long serialVersionUID = 203011097255278833L;

    private String logId;

    private String logAccessUserId;

    private String logAccessUserName;

    private String logAccessType;

    private String logAccessUrl;

    private String logAccessIp;

    private Date logAccessTime;


    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogAccessUserId() {
        return logAccessUserId;
    }

    public void setLogAccessUserId(String logAccessUserId) {
        this.logAccessUserId = logAccessUserId;
    }

    public String getLogAccessUserName() {
        return logAccessUserName;
    }

    public void setLogAccessUserName(String logAccessUserName) {
        this.logAccessUserName = logAccessUserName;
    }

    public String getLogAccessType() {
        return logAccessType;
    }

    public void setLogAccessType(String logAccessType) {
        this.logAccessType = logAccessType;
    }

    public String getLogAccessUrl() {
        return logAccessUrl;
    }

    public void setLogAccessUrl(String logAccessUrl) {
        this.logAccessUrl = logAccessUrl;
    }

    public String getLogAccessIp() {
        return logAccessIp;
    }

    public void setLogAccessIp(String logAccessIp) {
        this.logAccessIp = logAccessIp;
    }

    public Date getLogAccessTime() {
        return logAccessTime;
    }

    public void setLogAccessTime(Date logAccessTime) {
        this.logAccessTime = logAccessTime;
    }

    @Override
    public String toString() {
        return "TLogger{" +
                "logId='" + logId + '\'' +
                ", logAccessUserId='" + logAccessUserId + '\'' +
                ", logAccessUserName='" + logAccessUserName + '\'' +
                ", logAccessType='" + logAccessType + '\'' +
                ", logAccessUrl='" + logAccessUrl + '\'' +
                ", logAccessIp='" + logAccessIp + '\'' +
                ", logAccessTime=" + logAccessTime +
                '}';
    }
}