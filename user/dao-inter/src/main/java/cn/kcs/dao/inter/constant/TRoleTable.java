
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TRole;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> ROLE * <!-- end-user-doc -->
 */
public class TRoleTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TRoleTable T_ROLE_TABLE = new TRoleTable();

    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ROLE_ID = new Column(this, "role_id");
    /**
     * <!-- begin-user-doc --> 角色名称 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ROLE_NAME = new Column(this, "role_name");
    /**
     * <!-- begin-user-doc --> 角色描述 * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ROLE_DESCRIPTION = new Column(this, "role_description");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRoleTable() {
        super("t_role");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRoleTable(String schemaName) {
        super(schemaName, "t_role");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRoleTable(String schemaName, String alias) {
        super(schemaName, "t_role", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TRoleTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_role", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TRole> getPojoType() {
        return TRole.class;
    }

}
