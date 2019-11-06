package cn.kcs.common.util;

import java.util.UUID;

/**
 * 随机码工具
 * @author kcs
 * @date 2018-10-26 11:25
 **/
public class RandomUtil {
    /**
     * 生成前缀+编码 用作id
     *
     * @return string
     */
    public static String generateCoding(String prefix) {
        return prefix.concat(CustomDateUtil.currentFormatDate(CustomDateUtil.DATE_TO_SHORT_PATTERN)).concat(generateRandomNumber(4));
    }

    public static String generateId() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    public static String generateRandomNumber(Integer length) {
        boolean b = length < 1 || length > 10;
        if (b) {
            length = 4;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer i = 0; i < length; i++) {
            int round = (Math.round((float) Math.round(Math.random() * 10)));
            stringBuffer.append(round);
        }
        return stringBuffer.toString();
    }
}
