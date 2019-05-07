package cn.kcs.order.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (Product)实体类
 *
 * @author makejava
 * @since 2019-04-23 14:38:33
 */
public class Product implements Serializable {
    private static final long serialVersionUID = -44935492307856190L;

    private String productId;

    private String productName;

    private String productPrise;

    private String productStatus;

    private String productType;

    private String productSalesNumber;

    private String productFileInfo;

    private String productDescription;

    private String productCreateBy;

    private String productUpdateBy;

    private Date productCreateTime;

    private Date productUpdateTime;

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public String getProductPrise() {
        return productPrise;
    }

    public void setProductPrise(String productPrise) {
        this.productPrise = productPrise;
    }

    public String getProductSalesNumber() {
        return productSalesNumber;
    }

    public void setProductSalesNumber(String productSalesNumber) {
        this.productSalesNumber = productSalesNumber;
    }

    public String getProductFileInfo() {
        return productFileInfo;
    }

    public void setProductFileInfo(String productFileInfo) {
        this.productFileInfo = productFileInfo;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCreateBy() {
        return productCreateBy;
    }

    public void setProductCreateBy(String productCreateBy) {
        this.productCreateBy = productCreateBy;
    }

    public String getProductUpdateBy() {
        return productUpdateBy;
    }

    public void setProductUpdateBy(String productUpdateBy) {
        this.productUpdateBy = productUpdateBy;
    }

    public Date getProductCreateTime() {
        return productCreateTime;
    }

    public void setProductCreateTime(Date productCreateTime) {
        this.productCreateTime = productCreateTime;
    }

    public Date getProductUpdateTime() {
        return productUpdateTime;
    }

    public void setProductUpdateTime(Date productUpdateTime) {
        this.productUpdateTime = productUpdateTime;
    }

}