package com.starcor.stb.venom.controller.upload;

import java.io.Serializable;

/**
 * Created by chongyang.gao on 2018/12/15.
 */
public class ResponseData implements Serializable{

    //失败状态码
    public static final String CODE_SIGN_FAILED = "-1";
    public static final String CODE_INTERNAL_ERROR = "-2";
    public static final String CODE_OTHER_ERROR = "-3";

    //成功状态码
    public static final String SUCCEED = "0";

    private String code;
    private String msg;
    private Object data;

    public ResponseData succeed() {
        return succeed("succeed");
    }

    public ResponseData succeed(Object obj) {
        return succeed(obj, "数据发送成功!");
    }

    public ResponseData succeed(Object obj, String msg) {
        code = SUCCEED;
        this.data = obj;
        this.msg = msg;
        return this;
    }


    public ResponseData failure() {
        return failure("未知错误");
    }

    public ResponseData failure(Object obj) {
        return failure(obj, CODE_OTHER_ERROR, "error");
    }

    public ResponseData failure(Object obj, String code, String msg) {
        this.code = code;
        this.data = obj;
        this.msg = msg;
        return this;
    }


}
