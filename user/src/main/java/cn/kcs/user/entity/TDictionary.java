package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TDictionary)实体类
 *
 * @author kcs
 * @since 2019-03-14 20:34:49
 */
public class TDictionary implements Serializable {
    private static final long serialVersionUID = 134725200183501677L;

    private String dictionaryId;

    private String dictionaryGroupName;

    private String dictionaryKey;

    private String dictionaryLabel;

    private String dictionaryIndex;

    private Date dictionaryCreateTime;


    public String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getDictionaryGroupName() {
        return dictionaryGroupName;
    }

    public void setDictionaryGroupName(String dictionaryGroupName) {
        this.dictionaryGroupName = dictionaryGroupName;
    }

    public String getDictionaryKey() {
        return dictionaryKey;
    }

    public void setDictionaryKey(String dictionaryKey) {
        this.dictionaryKey = dictionaryKey;
    }

    public String getDictionaryLabel() {
        return dictionaryLabel;
    }

    public void setDictionaryLabel(String dictionaryLabel) {
        this.dictionaryLabel = dictionaryLabel;
    }

    public String getDictionaryIndex() {
        return dictionaryIndex;
    }

    public void setDictionaryIndex(String dictionaryIndex) {
        this.dictionaryIndex = dictionaryIndex;
    }

    public Date getDictionaryCreateTime() {
        return dictionaryCreateTime;
    }

    public void setDictionaryCreateTime(Date dictionaryCreateTime) {
        this.dictionaryCreateTime = dictionaryCreateTime;
    }

    @Override
    public String toString() {
        return "TDictionary{" +
                "dictionaryId='" + dictionaryId + '\'' +
                ", dictionaryGroupName='" + dictionaryGroupName + '\'' +
                ", dictionaryKey='" + dictionaryKey + '\'' +
                ", dictionaryLabel='" + dictionaryLabel + '\'' +
                ", dictionaryIndex='" + dictionaryIndex + '\'' +
                ", dictionaryCreateTime=" + dictionaryCreateTime +
                '}';
    }
}