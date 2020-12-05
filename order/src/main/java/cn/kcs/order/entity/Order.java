package cn.kcs.order.entity;

import cn.kcs.order.entity.dto.SimpleProduct;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (Order)实体类
 *
 * @author kcs
 * @since 2019-04-24 14:42:54
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 978408228821311946L;

    private String orderId;

    private String orderNumber;

    private String orderCustomer;

    private String orderSerialNumber;

    private String orderStatus;

    private String orderType;

    private String orderDesk;

    private Date orderCreateTime;

    private Date orderSettlementTime;

    private List<SimpleProduct> products;

    public List<SimpleProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SimpleProduct> products) {
        this.products = products;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderCustomer() {
        return orderCustomer;
    }

    public void setOrderCustomer(String orderCustomer) {
        this.orderCustomer = orderCustomer;
    }

    public String getOrderSerialNumber() {
        return orderSerialNumber;
    }

    public void setOrderSerialNumber(String orderSerialNumber) {
        this.orderSerialNumber = orderSerialNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderDesk() {
        return orderDesk;
    }

    public void setOrderDesk(String orderDesk) {
        this.orderDesk = orderDesk;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Date getOrderSettlementTime() {
        return orderSettlementTime;
    }

    public void setOrderSettlementTime(Date orderSettlementTime) {
        this.orderSettlementTime = orderSettlementTime;
    }

}