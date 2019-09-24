package cn.kcs.order.entity.dto;

/**
 * @description: OrderGoods
 * @author: kcs
 * @date: 2019-05-21 16:10
 **/
public class OrderGoodsDto {
    private String orderId;
    private String orderSerialNumber;
    private String productId;
    private String productName;
    private String productDeskNumber;
    private String orderProductNumber;
    private String orderProductPrise;
    private String orderProductStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderSerialNumber() {
        return orderSerialNumber;
    }

    public void setOrderSerialNumber(String orderSerialNumber) {
        this.orderSerialNumber = orderSerialNumber;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDeskNumber() {
        return productDeskNumber;
    }

    public void setProductDeskNumber(String productDeskNumber) {
        this.productDeskNumber = productDeskNumber;
    }

    public String getOrderProductNumber() {
        return orderProductNumber;
    }

    public void setOrderProductNumber(String orderProductNumber) {
        this.orderProductNumber = orderProductNumber;
    }

    public String getOrderProductPrise() {
        return orderProductPrise;
    }

    public void setOrderProductPrise(String orderProductPrise) {
        this.orderProductPrise = orderProductPrise;
    }

    public String getOrderProductStatus() {
        return orderProductStatus;
    }

    public void setOrderProductStatus(String orderProductStatus) {
        this.orderProductStatus = orderProductStatus;
    }
}
