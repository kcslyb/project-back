package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (RolePermission)实体类
 *
 * @author kcs
 * @since 2019-10-14 10:25:07
 */
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 648713556933530311L;

    private String rolePermissionId;

    private String roleId;

    private String rolePermissionTitle;

    private String rolePermissionLabel;

    public String getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(String rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRolePermissionTitle() {
        return rolePermissionTitle;
    }

    public void setRolePermissionTitle(String rolePermissionTitle) {
        this.rolePermissionTitle = rolePermissionTitle;
    }

    public String getRolePermissionLabel() {
        return rolePermissionLabel;
    }

    public void setRolePermissionLabel(String rolePermissionLabel) {
        this.rolePermissionLabel = rolePermissionLabel;
    }

}