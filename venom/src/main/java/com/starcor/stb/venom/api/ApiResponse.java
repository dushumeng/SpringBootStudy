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
}
