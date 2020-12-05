package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Logger)实体类
 *
 * @author kcs
 * @since 2019-09-06 13:05:53
 */
public class LoggerDto implements Serializable {
    private static final long serialVersionUID = 289284932492663923L;

    private String logId;

    private String logUserId;

    private String logRequestUrl;

    private String logRequestRemoteIp;

    private String logRequestParameter;

    private String logRequestMethodAndInterface;

    private String logRequestPageName;

    private String logRequestDescribe;

    private Date logRequestTime;

    private String logType;


    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(String logUserId) {
        this.logUserId = logUserId;
    }

    public String getLogRequestUrl() {
        return logRequestUrl;
    }

    public void setLogRequestUrl(String logRequestUrl) {
        this.logRequestUrl = logRequestUrl;
    }

    public String getLogRequestRemoteIp() {
        return logRequestRemoteIp;
    }

    public void setLogRequestRemoteIp(String logRequestRemoteIp) {
        this.logRequestRemoteIp = logRequestRemoteIp;
    }

    public String getLogRequestParameter() {
        return logRequestParameter;
    }

    public void setLogRequestParameter(String logRequestParameter) {
        this.logRequestParameter = logRequestParameter;
    }

    public String getLogRequestMethodAndInterface() {
        return logRequestMethodAndInterface;
    }

    public void setLogRequestMethodAndInterface(String logRequestMethodAndInterface) {
        this.logRequestMethodAndInterface = logRequestMethodAndInterface;
    }

    public String getLogRequestPageName() {
        return logRequestPageName;
    }

    public void setLogRequestPageName(String logRequestPageName) {
        this.logRequestPageName = logRequestPageName;
    }

    public String getLogRequestDescribe() {
        return logRequestDescribe;
    }

    public void setLogRequestDescribe(String logRequestDescribe) {
        this.logRequestDescribe = logRequestDescribe;
    }

    public Date getLogRequestTime() {
        return logRequestTime;
    }

    public void setLogRequestTime(Date logRequestTime) {
        this.logRequestTime = logRequestTime;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

}