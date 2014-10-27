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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 简单的日期工具<br>
 * 2013-12-17 上午11:22:50
 * 
 * @author jacks808@163.com<br>
 */
public final class DateUtil {
    /**
     * @Deprecated 特别说明，SimpleDateFormat不是线程安全的。。。
     */
    public static final DateFormat FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(DEFAULT_PATTERN);

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
        return DEFAULT_DATE_FORMAT.format(date);
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
            return DEFAULT_DATE_FORMAT.parse(dateString);
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

    // 一天等于多少毫秒
    public static final int ONE_DAY_MS = 24 * 60 * 60 * 1000;

    // public static Date getDateStart(Date date) {
    // Date s = new Date(date.getTime());
    // s.setHours(0);
    // s.setMinutes(0);
    // s.setSeconds(0);
    //
    // return s;
    // }

    public static Date getDateEnd(Date date) {
        Date s = new Date(date.getTime() + 24 * 60 * 60 * 1000);
        return s;
    }

    /**
     * 
     */
    public static String getTodayYYYYMMDD() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getTomorrowYYYYMMDD() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);

        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 获取当天剩余的时间（秒），剩余0秒时按剩余1秒处理，因为0秒在缓存中是“不超时”的意思
     * 
     * @return
     */
    public static int getTodayLeftSec() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);

        int leftSec = (int) ((cal.getTimeInMillis() - System.currentTimeMillis()) / 1000);
        return leftSec == 0 ? 1 : leftSec;
    }

    /**
     * 判断莫个日期是否在“今天”，用于跟据不同的日期进行不同的格式化
     * 
     * @param date
     * @return
     */
    public static boolean isCurrentDay(Date date) {
        try {
            if (date == null) {
                return false;
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date current = new Date();
            if (df.format(current).equals(df.format(date))) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
