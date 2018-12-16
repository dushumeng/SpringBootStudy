package com.starcor.stb.venom.mvc;

import com.starcor.stb.venom.api.ApiResponse;

public abstract class BaseApiController {

    public ApiResponse wrapData(Object data) {
        return wrapData(ApiResponse.CODE.SUCCESS.value, null, data);
    }

    public ApiResponse wrapData(ApiResponse.CODE code, String msg) {
        return wrapData(code, msg, null);
    }

    public ApiResponse wrapData(ApiResponse.CODE code, String msg, Object data) {
        if (code == null) {
            code = ApiResponse.CODE.SERVER_ERROR;
        }
        return wrapData(code.value, msg, data);
    }

    public ApiResponse wrapData(int code, String msg, Object data) {
        ApiResponse res = new ApiResponse();
        res.code = code;
        res.msg = msg;
        res.data = data;
        return res;
    }
}
