package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (TRolePermission)实体类
 *
 * @author makejava
 * @since 2019-03-23 18:45:08
 */
public class TRolePermission implements Serializable {
    private static final long serialVersionUID = 876033886801841162L;

    private String rolePermissionId;

    private String roleId;

    private String permissionId;


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

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "TRolePermission{" +
                "rolePermissionId='" + rolePermissionId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}