package cn.kcs.user.entity.dto;

import cn.kcs.user.entity.UserAccount;
import cn.kcs.user.entity.UserDepartment;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: kcs
 * @create: 2019-03-22 16:54
 **/
public class DepartmentDto implements Serializable {

    private UserDepartment userDepartment;

    private List<UserAccount> userAccounts;

    public UserDepartment getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(UserDepartment userDepartment) {
        this.userDepartment = userDepartment;
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "userDepartment=" + userDepartment +
                ", userAccounts=" + userAccounts +
                '}';
    }
}
