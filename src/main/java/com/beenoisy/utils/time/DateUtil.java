/*
 * beenoisy-utils - com.beenoisy.utils.time.DateUtil.java
 * 2013-12-17 上午11:22:50
 *
 * jacks808@163.com
 *
 * KKKKKKKKK    KKKKKKK                                                          
 * K:::::::K    K:::::K                                                          
 * K:::::::K    K:::::K                                                          
 * K:::::::K   K::::::K                                                          
 * KK::::::K  K:::::KKK    eeeeeeeeeeee        eeeeeeeeeeee    nnnn  nnnnnnnn    
 *   K:::::K K:::::K     ee::::::::::::ee    ee::::::::::::ee  n:::nn::::::::nn  
 *   K::::::K:::::K     e::::::eeeee:::::ee e::::::eeeee:::::een::::::::::::::nn 
 *   K:::::::::::K     e::::::e     e:::::ee::::::e     e:::::enn:::::::::::::::n
 *   K:::::::::::K     e:::::::eeeee::::::ee:::::::eeeee::::::e  n:::::nnnn:::::n
 *   K::::::K:::::K    e:::::::::::::::::e e:::::::::::::::::e   n::::n    n::::n
 *   K:::::K K:::::K   e::::::eeeeeeeeeee  e::::::eeeeeeeeeee    n::::n    n::::n
 * KK::::::K  K:::::KKKe:::::::e           e:::::::e             n::::n    n::::n
 * K:::::::K   K::::::Ke::::::::e          e::::::::e            n::::n    n::::n
 * K:::::::K    K:::::K e::::::::eeeeeeee   e::::::::eeeeeeee    n::::n    n::::n
 * K:::::::K    K:::::K  ee:::::::::::::e    ee:::::::::::::e    n::::n    n::::n
 * KKKKKKKKK    KKKKKKK    eeeeeeeeeeeeee      eeeeeeeeeeeeee    nnnnnn    nnnnnn
 * 
 */
package com.beenoisy.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单的日期工具<br>
 * 2013-12-17 上午11:22:50
 * 
 * @author jacks808@163.com<br>
 */
public final class DateUtil {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_PATTERN);

    private DateUtil() {
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式转换Date为String <br>
     * jacks808@163.com<br>
     * 2013-12-17 上午11:32:54<br>
     * <br>
     * 
     * @param date
     * @return
     */
    public static String date2Str(Date date) {
        return formater.format(date);
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式转换String为Date <br>
     * jacks808@163.com<br>
     * 2013-12-17 上午11:33:29<br>
     * <br>
     * 
     * @param dateString
     * @return
     */
    public static Date str2Date(String dateString) {
        try {
            return formater.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成按照yyyy-MM-dd HH:mm:ss格式化好的当前时间 <br>
     * jacks808@163.com<br>
     * 2013-12-17 上午11:36:13<br>
     * <br>
     * 
     * @return
     */
    public static String getDateString() {
        return date2Str(new Date());
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss格式 <br>
     * jacks808@163.com<br>
     * 2013-12-17 上午11:33:56<br>
     * <br>
     * 
     * @return
     */
    public static String getFormate() {
        return DEFAULT_PATTERN;
    }
}
