
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TOrderProduct;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_ORDER_PRODUCT * <!-- end-user-doc -->
 */
public class TOrderProductTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TOrderProductTable T_ORDER_PRODUCT_TABLE = new TOrderProductTable();

    /**
     * <!-- begin-user-doc --> ORDER_PRODUCT_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_PRODUCT_ID = new Column(this, "order_product_id");
    /**
     * <!-- begin-user-doc --> ORDER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column ORDER_ID = new Column(this, "order_id");
    /**
     * <!-- begin-user-doc --> PRO_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_ID = new Column(this, "pro_id");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderProductTable() {
        super("t_order_product");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderProductTable(String schemaName) {
        super(schemaName, "t_order_product");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderProductTable(String schemaName, String alias) {
        super(schemaName, "t_order_product", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TOrderProductTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_order_product", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TOrderProduct> getPojoType() {
        return TOrderProduct.class;
    }

}
