package cn.kcs.user.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * (fileDto)实体类
 *
 * @author kcs 2020-12-12
 */
public class FileDto implements Serializable {
    private static final long serialVersionUID = -10712479757371921L;

    private String fileId;

    private String fileName;

    private String base64;

    private String fileDescription;

    private Date fileCreateTime;

    private String fileSize;

    private String fileType;

    private String fileOwner;

    private String fileOwnerName;

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
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

    @Override
    public String toString() {
        return "TFile{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + base64 + '\'' +
                ", fileDescription='" + fileDescription + '\'' +
                ", fileCreateTime=" + fileCreateTime +
                ", fileSize='" + fileSize + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileOwner='" + fileOwner + '\'' +
                ", fileOwnerName='" + fileOwnerName + '\'' +
                '}';
    }
}