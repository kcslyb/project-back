package cn.kcs.user.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author kcs
 * @since 2019-09-30 10:27:48
 */
public class User implements Serializable {
    private static final long serialVersionUID = -85903179903779332L;

    private String userId;

    private String userName;

    private String userAlias;

    private String userWord;

    private String userAccountId;

    private String userStatus;

    private String userMaritalStatus;

    private Date userBirthTime;

    private Date userLeaveTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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

    public Date getUserLeaveTime() {
        return userLeaveTime;
    }

    public void setUserLeaveTime(Date userLeaveTime) {
        this.userLeaveTime = userLeaveTime;
    }

}