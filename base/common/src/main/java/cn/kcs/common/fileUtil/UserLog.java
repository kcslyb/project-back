package cn.kcs.common.fileUtil;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @description:
 * @author: kcs
 * @create: 2018-10-23 15:16
 **/
public class UserLog {
    @Excel(name = "string 1")
    String string1;
    @Excel(name = "string 2")
    String string2;

    public UserLog(String string1, String string2) {
        this.string1 = string1;
        this.string2 = string2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }
}
