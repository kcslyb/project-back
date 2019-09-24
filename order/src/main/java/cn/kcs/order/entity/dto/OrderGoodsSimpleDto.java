package cn.kcs.order.entity.dto;

import java.util.List;

/**
 * @description:
 * @author: kcs
 * @date: 2019-05-21 17:25
 **/
public class OrderGoodsSimpleDto {
    private String orderId;

    private String orderProductNumber;

    private String orderProductStatus;

    private List<String> orderProductIds;

    public String getOrderProductStatus() {
        return orderProductStatus;
    }

    public void setOrderProductStatus(String orderProductStatus) {
        this.orderProductStatus = orderProductStatus;
    }

    public String getOrderProductNumber() {
        return orderProductNumber;
    }

    public void setOrderProductNumber(String orderProductNumber) {
        this.orderProductNumber = orderProductNumber;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<String> getOrderProductIds() {
        return orderProductIds;
    }

    public void setOrderProductIds(List<String> orderProductIds) {
        this.orderProductIds = orderProductIds;
    }
}
