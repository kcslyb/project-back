
package cn.kcs.dao.inter.constant;

import cn.kcs.dao.inter.pojo.TProduct;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc --> T_PRODUCT * <!-- end-user-doc -->
 */
public class TProductTable extends Table {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public static final TProductTable T_PRODUCT_TABLE = new TProductTable();

    /**
     * <!-- begin-user-doc --> PRO_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_ID = new Column(this, "pro_id");
    /**
     * <!-- begin-user-doc --> PRO_LABLE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_LABLE = new Column(this, "pro_lable");
    /**
     * <!-- begin-user-doc --> PRO_PRISE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_PRISE = new Column(this, "pro_prise");
    /**
     * <!-- begin-user-doc --> PRO_PATH * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_PATH = new Column(this, "pro_path");
    /**
     * <!-- begin-user-doc --> PRO_SRC * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_SRC = new Column(this, "pro_src");
    /**
     * <!-- begin-user-doc --> PRO_TYPE * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_TYPE = new Column(this, "pro_type");
    /**
     * <!-- begin-user-doc --> PRO_CREATTIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_CREATTIME = new Column(this, "pro_creattime");
    /**
     * <!-- begin-user-doc --> PRO_MODIFYTIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public final Column PRO_MODIFYTIME = new Column(this, "pro_modifytime");

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TProductTable() {
        super("t_product");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TProductTable(String schemaName) {
        super(schemaName, "t_product");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TProductTable(String schemaName, String alias) {
        super(schemaName, "t_product", alias);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public TProductTable(String schemaName, String alias, boolean withAs) {
        super(schemaName, "t_product", alias, withAs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Class<TProduct> getPojoType() {
        return TProduct.class;
    }

}
