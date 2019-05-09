package cn.kcs.note.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (PhotoAlbum)实体类
 *
 * @author makejava
 * @since 2019-05-08 11:33:55
 */
public class PhotoAlbum implements Serializable {
    private static final long serialVersionUID = 291508848436918421L;

    private String photoId;

    private String photoName;

    private String photoPath;

    private String photoFileId;

    private String photoUploadBy;

    private Date photoUploadTime;


    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoFileId() {
        return photoFileId;
    }

    public void setPhotoFileId(String photoFileId) {
        this.photoFileId = photoFileId;
    }

    public String getPhotoUploadBy() {
        return photoUploadBy;
    }

    public void setPhotoUploadBy(String photoUploadBy) {
        this.photoUploadBy = photoUploadBy;
    }

    public Date getPhotoUploadTime() {
        return photoUploadTime;
    }

    public void setPhotoUploadTime(Date photoUploadTime) {
        this.photoUploadTime = photoUploadTime;
    }

    @Override
    public String toString() {
        return "PhotoAlbum{" +
                "photoId='" + photoId + '\'' +
                ", photoName='" + photoName + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", photoFileId='" + photoFileId + '\'' +
                ", photoUploadBy='" + photoUploadBy + '\'' +
                ", photoUploadTime=" + photoUploadTime +
                '}';
    }
}