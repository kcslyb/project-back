
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TOrder;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_ORDER * <!-- end-user-doc -->
 */
public class TOrderTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TOrderTable T_ORDER_TABLE = new TOrderTable();

    /**
     * <!-- begin-user-doc --> ORDER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_ID = new Column(this, "order_id");
    /**
     * <!-- begin-user-doc --> ORDER_INDEX * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_INDEX = new Column(this, "order_index");
    /**
     * <!-- begin-user-doc --> ORDER_CREATED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_CREATED = new Column(this, "order_created");
    /**
     * <!-- begin-user-doc --> ORDER_DESK * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_DESK = new Column(this, "order_desk");
    /**
     * <!-- begin-user-doc --> ORDER_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_CREATETIME = new Column(this, "order_createtime");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderTable() {
        super("t_order");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderTable(String schemaName) {
        super(schemaName, "t_order");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderTable(String schemaName, String alias) {
        super(schemaName, "t_order", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_order", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TOrder> getPojoType() {
        return TOrder.class;
    }

}
