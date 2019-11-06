package cn.kcs.user.entity.dto;

import cn.kcs.user.entity.RolePermission;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author kcs
 * @date 2019-10-14 10:28
 **/
public class RoleDto implements Serializable {
    private String roleId;

    private String roleName;

    private String roleDescription;

    private String roleType;

    private String roleDeleteFlag;

    private Date roleCreateTime;

    private Date roleUpdateTime;

    private String roleCreateBy;

    private String roleUpdateBy;

    private List<RolePermission> rolePermissionList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleDeleteFlag() {
        return roleDeleteFlag;
    }

    public void setRoleDeleteFlag(String roleDeleteFlag) {
        this.roleDeleteFlag = roleDeleteFlag;
    }

    public Date getRoleCreateTime() {
        return roleCreateTime;
    }

    public void setRoleCreateTime(Date roleCreateTime) {
        this.roleCreateTime = roleCreateTime;
    }

    public Date getRoleUpdateTime() {
        return roleUpdateTime;
    }

    public void setRoleUpdateTime(Date roleUpdateTime) {
        this.roleUpdateTime = roleUpdateTime;
    }

    public String getRoleCreateBy() {
        return roleCreateBy;
    }

    public void setRoleCreateBy(String roleCreateBy) {
        this.roleCreateBy = roleCreateBy;
    }

    public String getRoleUpdateBy() {
        return roleUpdateBy;
    }

    public void setRoleUpdateBy(String roleUpdateBy) {
        this.roleUpdateBy = roleUpdateBy;
    }

    public List<RolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<RolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }
}
