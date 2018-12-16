package com.starcor.stb.venom.api;

public class ApiResponse {

    public int code;
    public String msg;
    public Object data;

    public ApiResponse() {

    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
