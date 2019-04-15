
package cn.kcs.service.inter.dto;

import java.io.Serializable;

/**
 * <!-- begin-user-doc --> T_ORDER_PRODUCT * <!-- end-user-doc -->
 */
public class OrderProductDto implements Serializable {

    /**
     * <!-- begin-user-doc --> ORDER_PRODUCT_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String orderProductId;

    /**
     * <!-- begin-user-doc --> ORDER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String orderId;

    /**
     * <!-- begin-user-doc --> PRO_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String proId;

    /**
     * <!-- begin-user-doc --> ORDER_PRODUCT_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getOrderProductId() {
        return orderProductId;
    }

    /**
     * <!-- begin-user-doc --> ORDER_PRODUCT_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    /**
     * <!-- begin-user-doc --> ORDER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * <!-- begin-user-doc --> ORDER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * <!-- begin-user-doc --> PRO_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getProId() {
        return proId;
    }

    /**
     * <!-- begin-user-doc --> PRO_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setProId(String proId) {
        this.proId = proId;
    }

}
