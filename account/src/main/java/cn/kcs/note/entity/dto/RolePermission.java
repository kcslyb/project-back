package cn.kcs.note.entity.dto;

import cn.kcs.note.entity.TPermissionMenu;

import java.util.List;

/**
 * @description: role permssion
 * @author: kcs
 * @create: 2019-03-22 10:56
 **/
public class RolePermission {

    List<TPermissionMenu> permissions;
    private String roleId;
    private String roleName;
    private String roleDescription;

    public List<TPermissionMenu> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<TPermissionMenu> permissions) {
        this.permissions = permissions;
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
