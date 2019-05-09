package cn.kcs.note.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (TAccount)实体类
 *
 * @author makejava
 * @since 2019-02-25 09:39:21
 */
public class TAccount implements Serializable {
    private static final long serialVersionUID = 633274541696952836L;

    private String accountId;

    private String accountTitle;

    private String accountDescript;

    private Double accountPrise;

    private String accountRemark;

    private Date accountCreattime;

    private Date accountUpdatetime;

    private String accountCreatby;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    public String getAccountDescript() {
        return accountDescript;
    }

    public void setAccountDescript(String accountDescript) {
        this.accountDescript = accountDescript;
    }

    public Double getAccountPrise() {
        return accountPrise;
    }

    public void setAccountPrise(Double accountPrise) {
        this.accountPrise = accountPrise;
    }

    public String getAccountRemark() {
        return accountRemark;
    }

    public void setAccountRemark(String accountRemark) {
        this.accountRemark = accountRemark;
    }

    public Date getAccountCreattime() {
        return accountCreattime;
    }

    public void setAccountCreattime(Date accountCreattime) {
        this.accountCreattime = accountCreattime;
    }

    public Date getAccountUpdatetime() {
        return accountUpdatetime;
    }

    public void setAccountUpdatetime(Date accountUpdatetime) {
        this.accountUpdatetime = accountUpdatetime;
    }

    public String getAccountCreatby() {
        return accountCreatby;
    }

    public void setAccountCreatby(String accountCreatby) {
        this.accountCreatby = accountCreatby;
    }

    @Override
    public String toString() {
        return "TAccount{" +
                "accountId='" + accountId + '\'' +
                ", accountTitle='" + accountTitle + '\'' +
                ", accountDescript='" + accountDescript + '\'' +
                ", accountPrise=" + accountPrise +
                ", accountRemark='" + accountRemark + '\'' +
                ", accountCreattime=" + accountCreattime +
                ", accountUpdatetime=" + accountUpdatetime +
                ", accountCreatby='" + accountCreatby + '\'' +
                '}';
    }
}