package cn.kcs.order.entity.dto;

/**
 * @description: simple product
 * @author: kcs
 * @create: 2019-04-29 09:19
 **/
public class SimpleProduct {

    private String productId;

    private Integer productNumber;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }
}
