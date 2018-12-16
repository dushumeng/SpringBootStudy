package com.starcor.stb.core.util;

import org.apache.commons.lang3.StringUtils;

public class NumberUtils {

    public static long parseLong(String val) {
        return parseLong(val, 0);
    }

    public static long parseLong(String val, long defVal) {
        if (StringUtils.isEmpty(val)) {
            return defVal;
        }
        try {
            return Long.parseLong(val);
        } catch (Exception e) {
        }
        return defVal;
    }

}
