
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TSchedule;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> schedule * <!-- end-user-doc -->
 */
public class TScheduleTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TScheduleTable T_SCHEDULE_TABLE = new TScheduleTable();

    /**
     * <!-- begin-user-doc --> SCHEDULE_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column SCHEDULE_ID = new Column(this, "schedule_id");
    /**
     * <!-- begin-user-doc --> SCHEDULE_STATUS * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column SCHEDULE_STATUS = new Column(this, "schedule_status");
    /**
     * <!-- begin-user-doc --> SCHEDULE_ * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column SCHEDULE_CONFIRMED = new Column(this, "schedule_confirmed");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TScheduleTable() {
        super("t_schedule");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TScheduleTable(String schemaName) {
        super(schemaName, "t_schedule");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TScheduleTable(String schemaName, String alias) {
        super(schemaName, "t_schedule", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TScheduleTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_schedule", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TSchedule> getPojoType() {
        return TSchedule.class;
    }

}
