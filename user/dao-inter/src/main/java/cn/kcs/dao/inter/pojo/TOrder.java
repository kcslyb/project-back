
package cn.kcs.dao.inter.pojo;

import java.util.Date;

/**
 * <!-- begin-user-doc --> T_ORDER * <!-- end-user-doc -->
 */
public class TOrder {

    /**
     * <!-- begin-user-doc --> ORDER_ID * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String orderId;

    /**
     * <!-- begin-user-doc --> ORDER_INDEX * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String orderIndex;

    /**
     * <!-- begin-user-doc --> ORDER_CREATED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String orderCreated;

    /**
     * <!-- begin-user-doc --> ORDER_DESK * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private String orderDesk;

    /**
     * <!-- begin-user-doc --> ORDER_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private Date orderCreatetime;

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
     * <!-- begin-user-doc --> ORDER_INDEX * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getOrderIndex() {
        return orderIndex;
    }

    /**
     * <!-- begin-user-doc --> ORDER_INDEX * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setOrderIndex(String orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * <!-- begin-user-doc --> ORDER_CREATED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getOrderCreated() {
        return orderCreated;
    }

    /**
     * <!-- begin-user-doc --> ORDER_CREATED * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setOrderCreated(String orderCreated) {
        this.orderCreated = orderCreated;
    }

    /**
     * <!-- begin-user-doc --> ORDER_DESK * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public String getOrderDesk() {
        return orderDesk;
    }

    /**
     * <!-- begin-user-doc --> ORDER_DESK * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setOrderDesk(String orderDesk) {
        this.orderDesk = orderDesk;
    }

    /**
     * <!-- begin-user-doc --> ORDER_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    /**
     * <!-- begin-user-doc --> ORDER_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }

}
