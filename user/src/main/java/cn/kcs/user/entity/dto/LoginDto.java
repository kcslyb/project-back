package cn.kcs.user.entity.dto;

import java.io.Serializable;

/**
 * @author kcs
 * @date 2019-09-25 14:44
 **/
public class LoginDto implements Serializable {
    private String userName;
    private String passWord;
    private boolean remember;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
