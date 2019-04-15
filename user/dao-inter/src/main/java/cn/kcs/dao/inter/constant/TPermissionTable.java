
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TPermission;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> PERMISSION * <!-- end-user-doc -->
 */
public class TPermissionTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TPermissionTable T_PERMISSION_TABLE = new TPermissionTable();

    /**
     * <!-- begin-user-doc --> 权限id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PERMISSION_ID = new Column(this, "permission_id");
    /**
     * <!-- begin-user-doc --> 权限名称 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PERMISSION_NAME = new Column(this, "permission_name");
    /**
     * <!-- begin-user-doc --> 权限路径 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PERMISSION_URL = new Column(this, "permission_url");
    /**
     * <!-- begin-user-doc --> 权限方法 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PERMISSION_METHOD = new Column(this, "permission_method");
    /**
     * <!-- begin-user-doc --> 权限描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PERMISSION_DESCRIPTION = new Column(this, "permission_description");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TPermissionTable() {
        super("t_permission");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TPermissionTable(String schemaName) {
        super(schemaName, "t_permission");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TPermissionTable(String schemaName, String alias) {
        super(schemaName, "t_permission", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TPermissionTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_permission", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TPermission> getPojoType() {
        return TPermission.class;
    }

}
