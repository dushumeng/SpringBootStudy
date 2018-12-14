package com.starcor.stb.venom.api;

import javax.servlet.http.HttpServletRequest;

public class ApiHeader {

    public String clientOS;
    public String clientOSVersion;
    public String clientVersion;
    public String clientVersionName;
    public String clientVersionCode;
    public String deviceUUID;
    public String deviceModel;
    public String deviceBrand;
    public String clientMarket;
    public String deviceMac;
    public String userId;
    public long timestamp;
    public String sign;

    public static ApiHeader parse(HttpServletRequest request) {

        ApiHeader apiHeader = new ApiHeader();
        apiHeader.sign = request.getHeader("sign");
        apiHeader.timestamp = Long.valueOf(request.getHeader("timestamp"));
        apiHeader.deviceMac = request.getHeader("device_mac");
        apiHeader.userId = request.getHeader("user_id");
        apiHeader.clientMarket = request.getHeader("client_market");
        apiHeader.deviceBrand = request.getHeader("device_brand");
        apiHeader.deviceModel = request.getHeader("device_model");
        apiHeader.deviceUUID = request.getHeader("device_uuid");
        apiHeader.clientVersionCode = request.getHeader("client_versioncode");
        apiHeader.clientVersionName = request.getHeader("client_versionname");
        apiHeader.clientVersion = request.getHeader("client_version");
        apiHeader.clientOSVersion = request.getHeader("client_os_version");
        apiHeader.clientOS = request.getHeader("client_os");


        return apiHeader;
    }

    public boolean isValid() {
        return true;
    }
}