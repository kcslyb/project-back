package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (UserGenealogyRelation)实体类
 *
 * @author kcs
 * @since 2019-09-30 10:29:03
 */
public class UserGenealogyRelation implements Serializable {
    private static final long serialVersionUID = 703537420155601594L;

    private String userGenealogyRelationId;

    private String userId;

    private String userGenealogyId;

    private String userParentId;

    private String userParentSpouseId;

    private String userSpouseId;


    public String getUserGenealogyRelationId() {
        return userGenealogyRelationId;
    }

    public void setUserGenealogyRelationId(String userGenealogyRelationId) {
        this.userGenealogyRelationId = userGenealogyRelationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserGenealogyId() {
        return userGenealogyId;
    }

    public void setUserGenealogyId(String userGenealogyId) {
        this.userGenealogyId = userGenealogyId;
    }

    public String getUserParentId() {
        return userParentId;
    }

    public void setUserParentId(String userParentId) {
        this.userParentId = userParentId;
    }

    public String getUserParentSpouseId() {
        return userParentSpouseId;
    }

    public void setUserParentSpouseId(String userParentSpouseId) {
        this.userParentSpouseId = userParentSpouseId;
    }

    public String getUserSpouseId() {
        return userSpouseId;
    }

    public void setUserSpouseId(String userSpouseId) {
        this.userSpouseId = userSpouseId;
    }

}