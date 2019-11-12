package cn.kcs.order.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Memo)实体类
 *
 * @author kcs
 * @since 2019-11-08 18:23:49
 */
public class Memo implements Serializable {
    private static final long serialVersionUID = -69923475977134623L;

    private String id;

    private String type;

    private String title;

    private String remake;

    private Double overhead;

    private String createBy;

    private Date createTime;

    private Date happenTime;

    private boolean deleteFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public Double getOverhead() {
        return overhead;
    }

    public void setOverhead(Double overhead) {
        this.overhead = overhead;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}