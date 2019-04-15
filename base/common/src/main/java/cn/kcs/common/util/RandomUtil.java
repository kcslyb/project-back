package cn.kcs.common.util;

import java.util.UUID;

/**
 * @description:随机码工具
 * @author: kcs
 * @create: 2018-10-26 11:25
 **/
public class RandomUtil {
    /**
     * 生成前缀+32位编码 用作id
     *
     * @return string
     */
    public static String genarateId(String prefix) {
        String uuid = prefix + UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
