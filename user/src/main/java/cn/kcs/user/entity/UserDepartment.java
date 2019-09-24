package cn.kcs.user.entity;

import java.io.Serializable;

/**
 * (UserDepartment)实体类
 *
 * @author makejava
 * @since 2019-03-22 10:08:43
 */
public class UserDepartment implements Serializable {
    private static final long serialVersionUID = -11311806997617623L;

    private String departmentId;

    private String departmentName;

    private String departmentNumber;

    private String departmentDescribe;

    public UserDepartment(String departmentId, String departmentName, String departmentNumber, String departmentDescribe) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentNumber = departmentNumber;
        this.departmentDescribe = departmentDescribe;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentDescribe() {
        return departmentDescribe;
    }

    public void setDepartmentDescribe(String departmentDescribe) {
        this.departmentDescribe = departmentDescribe;
    }

    @Override
    public String toString() {
        return "UserDepartment{" +
                "departmentId='" + departmentId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentNumber='" + departmentNumber + '\'' +
                ", departmentDescribe='" + departmentDescribe + '\'' +
                '}';
    }
}