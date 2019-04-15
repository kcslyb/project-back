package cn.kcs.common.util;

/**
 * @description: 判断是否为空
 * @author: kcs
 * @create: 2018-10-26 11:25
 **/
public class StringTools {

    public static boolean isNullOrEmpty(String str) {
        return null == str || "".equals(str) || "null".equals(str);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return null == obj || "".equals(obj);
    }
}
