package cn.kcs.schedule.entity;

import java.io.Serializable;

/**
 * (ConvertProduct)实体类
 *
 * @author kcs
 * @since 2020-09-17 20:19:17
 */
public class ConvertProduct implements Serializable {
    private static final long serialVersionUID = 356225178970179695L;

    private String id;

    private String productWorkId;

    private Double typeNumber;

    private String typeUnit;

    private String typeUnitName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductWorkId() {
        return productWorkId;
    }

    public void setProductWorkId(String productWorkId) {
        this.productWorkId = productWorkId;
    }

    public Double getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(Double typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getTypeUnit() {
        return typeUnit;
    }

    public void setTypeUnit(String typeUnit) {
        this.typeUnit = typeUnit;
    }

    public String getTypeUnitName() {
        return typeUnitName;
    }

    public void setTypeUnitName(String typeUnitName) {
        this.typeUnitName = typeUnitName;
    }

}