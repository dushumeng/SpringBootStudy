package com.starcor.stb.core.util;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {


    public static String md5(String str) {
        String s = DigestUtils.md5Hex(str);
        return s;
    }

}
