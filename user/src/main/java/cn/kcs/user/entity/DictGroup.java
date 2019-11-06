package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (DictGroup)实体类
 *
 * @author kcs
 * @since 2019-11-01 11:37:54
 */
public class DictGroup implements Serializable {
    private static final long serialVersionUID = 864068226501348192L;

    private String id;

    private String name;

    private String label;

    private String deleteFlag;

    private Date createTime;

    private Date modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}