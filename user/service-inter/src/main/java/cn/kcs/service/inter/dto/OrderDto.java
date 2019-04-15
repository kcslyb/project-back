
package cn.kcs.service.inter.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <!-- begin-user-doc --> T_ORDER * <!-- end-user-doc -->
 */
public class OrderDto implements Serializable {

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
    private DeskDto deskDto;

    /**
     * <!-- begin-user-doc --> ORDER_TIME * <!-- end-user-doc -->
     *
     * @modifiable
     */
    private Date orderCreatetime;

    private List<ProDto> proDtos;

    private String orderStatus;

    private String orderConfirmed;
    private String orderPrise;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderConfirmed() {
        return orderConfirmed;
    }

    public void setOrderConfirmed(String orderConfirmed) {
        this.orderConfirmed = orderConfirmed;
    }

    public String getOrderPrise() {
        return orderPrise;
    }

    public void setOrderPrise(String orderPrise) {
        this.orderPrise = orderPrise;
    }

    public DeskDto getDeskDto() {
        return deskDto;
    }

    public void setDeskDto(DeskDto deskDto) {
        this.deskDto = deskDto;
    }

    public List<ProDto> getProDtos() {
        return proDtos;
    }

    public void setProDtos(List<ProDto> proDtos) {
        this.proDtos = proDtos;
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
