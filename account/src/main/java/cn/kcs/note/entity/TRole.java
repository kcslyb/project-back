package cn.kcs.note.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (TRole)实体类
 *
 * @author makejava
 * @since 2019-03-22 10:37:46
 */
public class TRole implements Serializable {
    private static final long serialVersionUID = 259600980192059510L;

    private String roleId;

    private String roleName;

    private String roleDescription;

    private List<String> permissionIds;

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }

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

}