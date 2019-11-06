package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (Dict)实体类
 *
 * @author kcs
 * @since 2019-11-01 11:37:31
 */
public class Dict implements Serializable {
    private static final long serialVersionUID = 214170758129096129L;

    private String id;

    private String groupId;

    private String key;

    private String label;

    private Integer sort;

    private String remarks;

    private String deleteFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}