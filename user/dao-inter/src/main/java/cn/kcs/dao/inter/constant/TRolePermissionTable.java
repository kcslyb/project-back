
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TRolePermission;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_ROLE_PERMISSION * <!-- end-user-doc -->
 */
public class TRolePermissionTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TRolePermissionTable T_ROLE_PERMISSION_TABLE = new TRolePermissionTable();

    /**
     * <!-- begin-user-doc --> ROLE_PERMISSION_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ROLE_PERMISSION_ID = new Column(this, "role_permission_id");
    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ROLE_ID = new Column(this, "role_id");
    /**
     * <!-- begin-user-doc --> 权限id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PERMISSION_ID = new Column(this, "permission_id");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRolePermissionTable() {
        super("t_role_permission");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRolePermissionTable(String schemaName) {
        super(schemaName, "t_role_permission");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRolePermissionTable(String schemaName, String alias) {
        super(schemaName, "t_role_permission", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRolePermissionTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_role_permission", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TRolePermission> getPojoType() {
        return TRolePermission.class;
    }

}
