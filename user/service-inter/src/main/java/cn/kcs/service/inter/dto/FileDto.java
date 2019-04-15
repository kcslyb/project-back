
package cn.kcs.service.inter.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <!-- begin-user-doc --> T_FILE * <!-- end-user-doc -->
 */
public class FileDto implements Serializable {

    /**
     * <!-- begin-user-doc --> 文件id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String fileId;

    /**
     * <!-- begin-user-doc --> 文件名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String fileName;

    /**
     * <!-- begin-user-doc --> 文件路径 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String filePath;

    /**
     * <!-- begin-user-doc --> 文件描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String fileDescription;

    /**
     * <!-- begin-user-doc --> 上传时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private Date fileTime;

    /**
     * <!-- begin-user-doc --> 文件id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * <!-- begin-user-doc --> 文件id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * <!-- begin-user-doc --> 文件名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * <!-- begin-user-doc --> 文件名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * <!-- begin-user-doc --> 文件路径 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * <!-- begin-user-doc --> 文件路径 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * <!-- begin-user-doc --> 文件描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getFileDescription() {
        return fileDescription;
    }

    /**
     * <!-- begin-user-doc --> 文件描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    /**
     * <!-- begin-user-doc --> 上传时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Date getFileTime() {
        return fileTime;
    }

    /**
     * <!-- begin-user-doc --> 上传时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

}
