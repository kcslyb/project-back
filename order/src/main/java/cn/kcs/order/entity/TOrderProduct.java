package cn.kcs.order.entity;

import java.io.Serializable;

/**
 * (TOrderProduct)实体类
 *
 * @author makejava
 * @since 2019-04-28 22:46:37
 */
public class TOrderProduct implements Serializable {
    private static final long serialVersionUID = -38612150408115485L;

    private String orderProductId;

    private String orderId;

    private String productId;

    private Integer orderProductNumber;

    public String getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getOrderProductNumber() {
        return orderProductNumber;
    }

    public void setOrderProductNumber(Integer orderProductNumber) {
        this.orderProductNumber = orderProductNumber;
    }
}