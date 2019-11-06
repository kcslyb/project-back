package cn.kcs.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * data util
 * @author kcs
 * @date 2018-10-16 13:21
 **/
public class CustomDateUtil {
    /** 年-月-日 时:分:秒 显示格式 */
    public static String DATE_TO_STRING_DETAIL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 年-月-日 显示格式 */
    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

    /**
     * 年-月-日 显示格式
     */
    public static String DATE_TO_SHORT_PATTERN = "yyyyMMdd";

    private static SimpleDateFormat simpleDateFormat;

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String DateToString(Date source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }
    /**
     *
     * 字符串转换为对应日期(可能会报错异常)
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date stringToDate(String source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {
           e.getErrorOffset();
        }
        return date;
    }
    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern) {
        Calendar cal = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(cal.getTime());

    }

    /**
     * 获得当前时间 date格式
     *
     * @return
     */
    public static Date currentFormatDate() {
        return CustomDateUtil.stringToDate(CustomDateUtil.currentFormatDate(CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN), CustomDateUtil.DATE_TO_STRING_DETAIL_PATTERN);
    }


    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
    /**
     *
     * unix时间戳转为指定格式的String类型
     *
     *
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeStampToString(long source, String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source * 1000);
        return simpleDateFormat.format(date);
    }

}
