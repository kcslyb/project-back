package cn.kcs.note.entity.dto;

import cn.kcs.note.entity.TDictionary;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2019-03-15 19:44
 **/
public class DictionaryDto implements Serializable {
    private String groupName;
    private String groupNameValue;
    private List<TDictionary> dictionaries;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupNameValue() {
        return groupNameValue;
    }

    public void setGroupNameValue(String groupNameValue) {
        this.groupNameValue = groupNameValue;
    }

    public List<TDictionary> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<TDictionary> dictionaries) {
        this.dictionaries = dictionaries;
    }

    @Override
    public String toString() {
        return "DictionaryDto{" +
                "groupName='" + groupName + '\'' +
                ", groupNameValue='" + groupNameValue + '\'' +
                ", dictionaries=" + dictionaries +
                '}';
    }
}
