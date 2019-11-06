package cn.kcs.user.entity.dto;

import java.util.Date;

/**
 * @author kcs
 * @date 2019-09-30 17:27
 **/
public class UserDto {

    private String userAlias;

    private String userWord;

    private String userAccountId;

    private String userMaritalStatus;

    private Date userBirthTime;

    private String userGenealogyId;

    private String userParentId;

    private String userParentSpouseId;

    private String userSpouseId;

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getUserWord() {
        return userWord;
    }

    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserMaritalStatus() {
        return userMaritalStatus;
    }

    public void setUserMaritalStatus(String userMaritalStatus) {
        this.userMaritalStatus = userMaritalStatus;
    }

    public Date getUserBirthTime() {
        return userBirthTime;
    }

    public void setUserBirthTime(Date userBirthTime) {
        this.userBirthTime = userBirthTime;
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
