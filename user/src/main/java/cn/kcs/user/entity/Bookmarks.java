package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Bookmarks)实体类
 *
 * @author makejava
 * @since 2021-05-21 11:54:44
 */
public class Bookmarks implements Serializable {
    private static final long serialVersionUID = 136208383961376236L;

    private String id;

    private String title;

    private String href;

    private String iconFileId;

    private Date createTime;

    private String createBy;

    private String sort;

    private String deleteFlag;

    private String commonFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIconFileId() {
        return iconFileId;
    }

    public void setIconFileId(String iconFileId) {
        this.iconFileId = iconFileId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCommonFlag() {
        return commonFlag;
    }

    public void setCommonFlag(String commonFlag) {
        this.commonFlag = commonFlag;
    }

}