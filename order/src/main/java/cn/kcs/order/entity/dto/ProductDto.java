package cn.kcs.order.entity.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: product dto
 * @author: kcs
 * @create: 2019-04-23 15:22
 **/
public class ProductDto implements Serializable {

    private String productId;

    private String productName;

    private String productPrise;

    private String productStatus;

    private String productType;

    private String productSalesNumber;

    private String productFileId;

    private String productFilePath;

    private String productDescription;

    private String productCreateBy;

    private String productCreateByName;

    private String productUpdateBy;

    private String productUpdateByName;

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

    public String getProductFileId() {
        return productFileId;
    }

    public void setProductFileId(String productFileId) {
        this.productFileId = productFileId;
    }

    public String getProductFilePath() {
        return productFilePath;
    }

    public void setProductFilePath(String productFilePath) {
        this.productFilePath = productFilePath;
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

    public String getProductCreateByName() {
        return productCreateByName;
    }

    public void setProductCreateByName(String productCreateByName) {
        this.productCreateByName = productCreateByName;
    }

    public String getProductUpdateBy() {
        return productUpdateBy;
    }

    public void setProductUpdateBy(String productUpdateBy) {
        this.productUpdateBy = productUpdateBy;
    }

    public String getProductUpdateByName() {
        return productUpdateByName;
    }

    public void setProductUpdateByName(String productUpdateByName) {
        this.productUpdateByName = productUpdateByName;
    }
}
