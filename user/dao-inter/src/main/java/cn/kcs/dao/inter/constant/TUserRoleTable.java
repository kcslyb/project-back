
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TUserRole;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_USER_ROLE * <!-- end-user-doc -->
 */
public class TUserRoleTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TUserRoleTable T_USER_ROLE_TABLE = new TUserRoleTable();

    /**
     * <!-- begin-user-doc --> USER_ROLE_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column USER_ROLE_ID = new Column(this, "user_role_id");
    /**
     * <!-- begin-user-doc --> USER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column USER_ID = new Column(this, "user_id");
    /**
     * <!-- begin-user-doc --> 角色id * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ROLE_ID = new Column(this, "role_id");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TUserRoleTable() {
        super("t_user_role");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TUserRoleTable(String schemaName) {
        super(schemaName, "t_user_role");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TUserRoleTable(String schemaName, String alias) {
        super(schemaName, "t_user_role", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TUserRoleTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_user_role", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TUserRole> getPojoType() {
        return TUserRole.class;
    }

}
