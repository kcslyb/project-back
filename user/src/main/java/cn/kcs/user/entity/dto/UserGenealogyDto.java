package cn.kcs.user.entity.dto;

import cn.kcs.user.entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author kcs
 * @date 2019-09-30 11:11
 **/
public class UserGenealogyDto implements Serializable {
    private User self;
    private User spouse;
    private List<User> childList;
    private List<User> brotherList;
    private List<User> spouseBrotherList;
    private List<User> spouseParents;
    private List<User> selfParents;

    public List<User> getSpouseBrotherList() {
        return spouseBrotherList;
    }

    public void setSpouseBrotherList(List<User> spouseBrotherList) {
        this.spouseBrotherList = spouseBrotherList;
    }

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public User getSpouse() {
        return spouse;
    }

    public void setSpouse(User spouse) {
        this.spouse = spouse;
    }

    public List<User> getBrotherList() {
        return brotherList;
    }

    public void setBrotherList(List<User> brotherList) {
        this.brotherList = brotherList;
    }

    public List<User> getChildList() {
        return childList;
    }

    public void setChildList(List<User> childList) {
        this.childList = childList;
    }

    public List<User> getSpouseParents() {
        return spouseParents;
    }

    public void setSpouseParents(List<User> spouseParents) {
        this.spouseParents = spouseParents;
    }

    public List<User> getSelfParents() {
        return selfParents;
    }

    public void setSelfParents(List<User> selfParents) {
        this.selfParents = selfParents;
    }
}
