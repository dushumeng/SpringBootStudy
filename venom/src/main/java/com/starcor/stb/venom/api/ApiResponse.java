package com.starcor.stb.venom.api;

public class ApiResponse {

    public enum CODE {

        SIGN_ERROR(-1), SERVER_ERROR(-2), SUCCESS(0), FAIL(-3);

        public final int value;

        private CODE(int value) {
            this.value = value;
        }
    }


    public int code;
    public String msg = "";
    public Object data;

    public ApiResponse() {

    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiResponse createError(CODE code, String msg) {
        if (code == null) {
            code = CODE.FAIL;
        }
        return new ApiResponse(code.value, msg);
    }

    public static ApiResponse createError(String msg) {
        return createError(CODE.FAIL, msg);
    }

    public static ApiResponse createSuccess(String msg, Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.code = CODE.SUCCESS.value;
        apiResponse.msg = msg;
        apiResponse.data = data;
        return apiResponse;
    }

    public static ApiResponse createSuccess(String msg) {
        return createSuccess(msg, null);
    }
}
