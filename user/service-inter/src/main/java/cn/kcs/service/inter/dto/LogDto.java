package cn.kcs.service.inter.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: log dto
 * @author: kcs
 * @create: 2018-11-06 16:12
 **/
public class LogDto implements Serializable {
    /**
     * <!-- begin-user-doc --> LOG_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String logId;

    /**
     * <!-- begin-user-doc --> LOG_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private Date logTime;

    /**
     * <!-- begin-user-doc --> LOG_USED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String logUsed;

    /**
     * <!-- begin-user-doc --> LOG_OPERATE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String logOperate;

    /**
     * <!-- begin-user-doc --> LOG_ACCESSURL * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String logAccessurl;

    /**
     * <!-- begin-user-doc --> LOG_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getLogId() {
        return logId;
    }

    /**
     * <!-- begin-user-doc --> LOG_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * <!-- begin-user-doc --> LOG_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Date getLogTime() {
        return logTime;
    }

    /**
     * <!-- begin-user-doc --> LOG_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    /**
     * <!-- begin-user-doc --> LOG_USED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getLogUsed() {
        return logUsed;
    }

    /**
     * <!-- begin-user-doc --> LOG_USED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setLogUsed(String logUsed) {
        this.logUsed = logUsed;
    }

    /**
     * <!-- begin-user-doc --> LOG_OPERATE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getLogOperate() {
        return logOperate;
    }

    /**
     * <!-- begin-user-doc --> LOG_OPERATE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setLogOperate(String logOperate) {
        this.logOperate = logOperate;
    }

    /**
     * <!-- begin-user-doc --> LOG_ACCESSURL * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getLogAccessurl() {
        return logAccessurl;
    }

    /**
     * <!-- begin-user-doc --> LOG_ACCESSURL * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setLogAccessurl(String logAccessurl) {
        this.logAccessurl = logAccessurl;
    }

}