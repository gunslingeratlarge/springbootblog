package cn.kvmial.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * 将Date对象转换成字符串的工具类
 *
 * @author Kvmial
 * @version V1.0
 * @date 2018/8/3 0003 下午 13:17
 */


public final class DateUtils {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static final String TIME_PATTERN = "HH:mm:ss";

    public static final String TIME_SIMPLE_PATTERN = "HH:mm";


    public static String dateTimeToStr(Date date) {
        if (date != null) {
           return new SimpleDateFormat(DateUtils.DATETIME_PATTERN).format(date);

        }
        return "";
    }
}