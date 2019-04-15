
package cn.kcs.dao.inter.pojo;

/**
 * <!-- begin-user-doc --> T_ROLE_PERMISSION * <!-- end-user-doc -->
 */
public class TRolePermission {

    /**
     * <!-- begin-user-doc --> ROLE_PERMISSION_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String rolePermissionId;

    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String roleId;

    /**
     * <!-- begin-user-doc --> 权限id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String permissionId;

    /**
     * <!-- begin-user-doc --> ROLE_PERMISSION_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getRolePermissionId() {
        return rolePermissionId;
    }

    /**
     * <!-- begin-user-doc --> ROLE_PERMISSION_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setRolePermissionId(String rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * <!-- begin-user-doc --> 权限id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getPermissionId() {
        return permissionId;
    }

    /**
     * <!-- begin-user-doc --> 权限id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

}
