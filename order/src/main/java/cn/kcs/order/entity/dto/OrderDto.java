package cn.kcs.order.entity.dto;

import cn.kcs.order.entity.Desk;
import cn.kcs.order.entity.Product;
import cn.kcs.user.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * @description: order dto
 * @author: kcs
 * @create: 2019-04-24 14:36
 **/
public class OrderDto {

    private String orderId;

    private String orderNumber;

    private String orderCustomer;

    private String orderCustomerName;

    private String orderSerialNumber;

    private String orderStatus;

    private String orderType;

    private String orderDesk;

    private Desk desk;

    private Address address;

    private Integer orderProductSize;

    private Date orderCreateTime;

    private Date orderSettlementTime;

    private List<Product> products;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getOrderProductSize() {
        return orderProductSize;
    }

    public void setOrderProductSize(Integer orderProductSize) {
        this.orderProductSize = orderProductSize;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
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

    public String getOrderCustomerName() {
        return orderCustomerName;
    }

    public void setOrderCustomerName(String orderCustomerName) {
        this.orderCustomerName = orderCustomerName;
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

    public Desk getDesk() {
        return desk;
    }

    public void setDesk(Desk desk) {
        this.desk = desk;
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
