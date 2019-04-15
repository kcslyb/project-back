
package cn.kcs.dao.inter.pojo;

/**
 * <!-- begin-user-doc --> ROLE * <!-- end-user-doc -->
 */
public class TRole {

    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String roleId;

    /**
     * <!-- begin-user-doc --> 角色名称 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String roleName;

    /**
     * <!-- begin-user-doc --> 角色描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String roleDescription;

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
     * <!-- begin-user-doc --> 角色名称 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * <!-- begin-user-doc --> 角色名称 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * <!-- begin-user-doc --> 角色描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getRoleDescription() {
        return roleDescription;
    }

    /**
     * <!-- begin-user-doc --> 角色描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

}
