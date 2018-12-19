package com.starcor.stb.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_FORMAT_3 = "yyyyMMdd";
    /**
     * 日期格式：yyyyMMddHHmmss
     */
    public static final String DATE_FORMAT_4 = "yyyyMMddHHmmss";
    /**
     * 日期格式：yyyy年MM月dd日 HH:mm:ss
     */
    public static final String DATE_FORMAT_5 = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * 日期格式：yyyyMMddHHmmsss
     */
    public static final String DATE_FORMAT_6 = "yyyyMMddHHmmsss";

    private static final Map<String, SimpleDateFormat> dateFormatMap = new HashMap<>();

    static {
        dateFormatMap.put(DATE_FORMAT_1, new SimpleDateFormat(DATE_FORMAT_1));
        dateFormatMap.put(DATE_FORMAT_2, new SimpleDateFormat(DATE_FORMAT_2));
        dateFormatMap.put(DATE_FORMAT_3, new SimpleDateFormat(DATE_FORMAT_3));
        dateFormatMap.put(DATE_FORMAT_4, new SimpleDateFormat(DATE_FORMAT_4));
        dateFormatMap.put(DATE_FORMAT_5, new SimpleDateFormat(DATE_FORMAT_5));

    }

    public static String parse(long time) {
        return parse(time, DATE_FORMAT_1);
    }

    public static String parse(long time, String format) {
        try {
            SimpleDateFormat dateFormat = getFormat(format);
            return dateFormat.format(new Date(time));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public synchronized static SimpleDateFormat getFormat(String format) {
        SimpleDateFormat dateFormat = dateFormatMap.get(format);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(format);
            dateFormatMap.put(format, dateFormat);
        }
        return dateFormat;
    }

}
