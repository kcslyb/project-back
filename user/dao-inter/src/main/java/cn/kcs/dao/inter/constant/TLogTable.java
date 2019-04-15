
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TLog;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_LOG * <!-- end-user-doc -->
 */
public class TLogTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TLogTable T_LOG_TABLE = new TLogTable();

    /**
     * <!-- begin-user-doc --> LOG_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column LOG_ID = new Column(this, "log_id");
    /**
     * <!-- begin-user-doc --> LOG_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column LOG_TIME = new Column(this, "log_time");
    /**
     * <!-- begin-user-doc --> LOG_USED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column LOG_USED = new Column(this, "log_used");
    /**
     * <!-- begin-user-doc --> LOG_OPERATE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column LOG_OPERATE = new Column(this, "log_operate");
    /**
     * <!-- begin-user-doc --> LOG_ACCESSURL * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column LOG_ACCESSURL = new Column(this, "log_accessurl");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TLogTable() {
        super("t_log");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TLogTable(String schemaName) {
        super(schemaName, "t_log");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TLogTable(String schemaName, String alias) {
        super(schemaName, "t_log", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TLogTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_log", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TLog> getPojoType() {
        return TLog.class;
    }

}
