package com.starcor.stb.venom.controller;

import com.starcor.stb.venom.api.ApiResponse;

public abstract class BaseController {

    public ApiResponse wrapData(Object data) {
        return wrapData(0, null, data);
    }

    public ApiResponse wrapData(int code, String msg, Object data) {
        ApiResponse res = new ApiResponse();
        res.code = code;
        res.msg = msg;
        res.data = data;
        return res;
    }
}
