package cn.kcs.user.entity.dto;

import cn.kcs.user.entity.RolePermission;

import java.util.List;


/**
 * @author kcs
 * @date 2019-10-14 11:05
 **/
public class RolePermissionRequest {
    private String roleId;
    private List<RolePermission> permissionList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<RolePermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<RolePermission> permissionList) {
        this.permissionList = permissionList;
    }
}
