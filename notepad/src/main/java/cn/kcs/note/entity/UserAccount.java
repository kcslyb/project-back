package cn.kcs.note.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (UserAccount)实体类
 *
 * @author makejava
 * @since 2019-03-21 14:46:59
 */
public class UserAccount implements Serializable {
    private static final long serialVersionUID = -26066560086138494L;

    private String userId;

    private String userName;

    private String userPhone;

    private String userEmail;

    private String userAvatar;

    private String userRole;

    private String userDepartment;

    private String userPassword;

    private String userStatus;

    private Date userLastLoginTime;

    private Date userCreateTime;

    private Date userUpdateTime;

    private String userRoleName;

    private List<TPermissionMenu> userPermission;

    private String userDepartmentName;

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public List<TPermissionMenu> getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(List<TPermissionMenu> userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserDepartmentName() {
        return userDepartmentName;
    }

    public void setUserDepartmentName(String userDepartmentName) {
        this.userDepartmentName = userDepartmentName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Date userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

}