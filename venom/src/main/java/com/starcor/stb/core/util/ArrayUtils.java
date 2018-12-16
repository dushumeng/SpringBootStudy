package com.starcor.stb.core.util;

import java.util.Collection;

public class ArrayUtils {

    /**
     * 判断集合不为空
     *
     * @param c
     * @return true表示集合不为空
     */
    public static boolean isNotEmpty(Collection c) {
        if (null != c && c.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断集合为空
     *
     * @param c 集合
     * @return true表示集合为空
     */
    public static boolean isEmpty(Collection c) {
        return !isNotEmpty(c);
    }

}
