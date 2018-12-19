package com.starcor.stb.venom.log;

import com.starcor.stb.venom.VenomApplication;

public final class Logger {

    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(VenomApplication.class);

    public static void i(String msg) {
        logger.info(msg);
    }

    public static void e(String msg) {
        logger.error(msg);
    }

    public static void d(String msg) {
        logger.debug(msg);
    }

    public static void i(String... msgs) {
        if (msgs == null || msgs.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : msgs){
            sb.append(msg).append(" ");
        }
        logger.info(sb.toString());
    }

    public static void d(String... msgs) {
        if (msgs == null || msgs.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : msgs){
            sb.append(msg).append(" ");
        }
        logger.debug(sb.toString());
    }

    public static void e(String... msgs) {
        if (msgs == null || msgs.length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : msgs){
            sb.append(msg).append(" ");
        }
        logger.error(sb.toString());
    }
}
