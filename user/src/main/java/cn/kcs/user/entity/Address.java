package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (Address)实体类
 *
 * @author makejava
 * @since 2019-06-05 14:24:49
 */
public class Address implements Serializable {
    private static final long serialVersionUID = -98122620014385095L;

    private String addressId;

    private String addressName;

    private String addressDetail;

    private String addressForUser;

    private Integer addressDeleteFlag;


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressForUser() {
        return addressForUser;
    }

    public void setAddressForUser(String addressForUser) {
        this.addressForUser = addressForUser;
    }

    public Integer getAddressDeleteFlag() {
        return addressDeleteFlag;
    }

    public void setAddressDeleteFlag(Integer addressDeleteFlag) {
        this.addressDeleteFlag = addressDeleteFlag;
    }

}