package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TFile)实体类
 *
 * @author makejava
 * @since 2019-04-19 15:17:07
 */
public class TFile implements Serializable {
    private static final long serialVersionUID = -10712479757371921L;

    private String fileId;

    private String fileName;

    private String filePath;

    private String fileDescription;

    private Date fileCreateTime;

    private String fileSize;

    private String fileType;

    private String fileOwner;

    private String fileOwnerName;

    private String fileDownloadNumber;

    private String fileDeleteFlag;

    public String getFileDownloadNumber() {
        return fileDownloadNumber;
    }

    public void setFileDownloadNumber(String fileDownloadNumber) {
        this.fileDownloadNumber = fileDownloadNumber;
    }

    public String getFileOwner() {
        return fileOwner;
    }

    public void setFileOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    public String getFileOwnerName() {
        return fileOwnerName;
    }

    public void setFileOwnerName(String fileOwnerName) {
        this.fileOwnerName = fileOwnerName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileDeleteFlag() {
        return fileDeleteFlag;
    }

    public void setFileDeleteFlag(String fileDeleteFlag) {
        this.fileDeleteFlag = fileDeleteFlag;
    }

    @Override
    public String toString() {
        return "TFile{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", fileCreateTime=" + fileCreateTime +
                ", fileSize='" + fileSize + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileOwner='" + fileOwner + '\'' +
                ", fileOwnerName='" + fileOwnerName + '\'' +
                ", fileDownloadNumber='" + fileDownloadNumber + '\'' +
                '}';
    }
}