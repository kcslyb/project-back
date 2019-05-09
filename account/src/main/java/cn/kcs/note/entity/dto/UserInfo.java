package cn.kcs.note.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @description: user info
 * @author: kcs
 * @create: 2018-11-01 20:55
 **/
public class UserInfo implements Serializable {
    private String userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private String roleName;
    private String userAvatar;
    private List<String> menus;
    private List<String> permissions;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", roleName='" + roleName + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", menus=" + menus +
                ", permissions=" + permissions +
                '}';
    }
}
