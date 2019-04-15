package cn.kcs.service.inter.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * <!-- begin-user-doc --> 用户表 * <!-- end-user-doc -->
 */
public class UserDto implements Serializable {

    /**
     * <!-- begin-user-doc --> UUID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "userid", width = 30)
    private String uuid;

    /**
     * <!-- begin-user-doc --> 用户名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "username", width = 20)
    private String userName;

    /**
     * <!-- begin-user-doc --> 用户密码 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "userpassword", width = 20)
    private String userPassword;

    /**
     * <!-- begin-user-doc --> 用户电话号码 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "userphone", width = 20)
    private String userPhone;

    /**
     * <!-- begin-user-doc --> 用户邮箱 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "useremail", width = 30)
    private String userEmail;

    /**
     * <!-- begin-user-doc --> 用户状态 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "userstatus", width = 10)
    private String userStatus;

    /**
     * <!-- begin-user-doc --> 创建时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "createdate", width = 30)
    private Date createdata;

    /**
     * <!-- begin-user-doc --> 修改时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    @Excel(name = "modifydate", width = 30)
    private Date modifydata;

    private RoleDto roleDto;

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    /**
     * <!-- begin-user-doc --> UUID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * <!-- begin-user-doc --> UUID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * <!-- begin-user-doc --> 用户名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * <!-- begin-user-doc --> 用户名 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <!-- begin-user-doc --> 用户密码 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * <!-- begin-user-doc --> 用户密码 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * <!-- begin-user-doc --> 用户电话号码 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * <!-- begin-user-doc --> 用户电话号码 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * <!-- begin-user-doc --> 用户邮箱 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * <!-- begin-user-doc --> 用户邮箱 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * <!-- begin-user-doc --> 用户状态 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * <!-- begin-user-doc --> 用户状态 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * <!-- begin-user-doc --> 创建时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setCreatedata(Date createdata) {
        this.createdata = createdata;
    }

    /**
     * <!-- begin-user-doc --> 创建时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Date getCreatedata() {
        return createdata;
    }

    /**
     * <!-- begin-user-doc --> 修改时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setModifydata(Date modifydata) {
        this.modifydata = modifydata;
    }

    /**
     * <!-- begin-user-doc --> 修改时间 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Date getModifydata() {
        return modifydata;
    }

}

