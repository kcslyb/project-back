
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TDesk;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_DESK * <!-- end-user-doc -->
 */
public class TDeskTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TDeskTable T_DESK_TABLE = new TDeskTable();

    /**
     * <!-- begin-user-doc --> DESK_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column DESK_ID = new Column(this, "desk_id");
    /**
     * <!-- begin-user-doc --> DESK_NUMBER * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column DESK_NUMBER = new Column(this, "desk_number");
    /**
     * <!-- begin-user-doc --> DESK_STATUS * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column DESK_STATUS = new Column(this, "desk_status");
    /**
     * <!-- begin-user-doc --> DESK_SIZE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column DESK_SIZE = new Column(this, "desk_size");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TDeskTable() {
        super("t_desk");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TDeskTable(String schemaName) {
        super(schemaName, "t_desk");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TDeskTable(String schemaName, String alias) {
        super(schemaName, "t_desk", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TDeskTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_desk", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TDesk> getPojoType() {
        return TDesk.class;
    }

}
