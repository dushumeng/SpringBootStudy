package com.starcor.stb.venom.api;

import com.starcor.stb.core.util.NumberUtils;
import com.starcor.stb.core.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class ApiHeader implements Serializable {

    public String clientOS;
    public String clientOSVersion;
    public String clientOSFirmware;
    public String appVersionName;
    public String appVersionCode;
    public String deviceUUID;
    public String deviceModel;
    public String deviceBrand;
    public String clientMarket;
    public String deviceMac;
    public String userId;
    public long timestamp;
    public String sign;
    public String ip;
    public String projectId;

    public static ApiHeader parse(HttpServletRequest request) {

        ApiHeader apiHeader = new ApiHeader();
        apiHeader.sign = WebUtils.urlDecoder(request.getHeader("sign"));
        apiHeader.timestamp = NumberUtils.parseLong(WebUtils.urlDecoder(request.getHeader("timestamp")));
        apiHeader.deviceMac = WebUtils.urlDecoder(request.getHeader("device_mac"));
        apiHeader.userId = WebUtils.urlDecoder(request.getHeader("user_id"));
        apiHeader.clientMarket = WebUtils.urlDecoder(request.getHeader("client_market"));
        apiHeader.deviceBrand = WebUtils.urlDecoder(request.getHeader("device_brand"));
        apiHeader.deviceModel = WebUtils.urlDecoder(request.getHeader("device_model"));
        apiHeader.deviceUUID = WebUtils.urlDecoder(request.getHeader("device_uuid"));
        apiHeader.appVersionName = WebUtils.urlDecoder(request.getHeader("app_versioncode"));
        apiHeader.appVersionCode = WebUtils.urlDecoder(request.getHeader("app_versionname"));
        apiHeader.clientOSFirmware = WebUtils.urlDecoder(request.getHeader("client_os_firmware"));
        apiHeader.clientOSVersion = WebUtils.urlDecoder(request.getHeader("client_os_version"));
        apiHeader.clientOS = WebUtils.urlDecoder(request.getHeader("client_os"));
        apiHeader.ip = WebUtils.getIpAddress(request);
        apiHeader.projectId = WebUtils.urlDecoder(request.getHeader("project_id"));

        return apiHeader;
    }

    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "ApiHeader{" +
                "clientOS='" + clientOS + '\'' +
                ", clientOSVersion='" + clientOSVersion + '\'' +
                ", clientOSFirmware='" + clientOSFirmware + '\'' +
                ", appVersionName='" + appVersionName + '\'' +
                ", appVersionCode='" + appVersionCode + '\'' +
                ", deviceUUID='" + deviceUUID + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", clientMarket='" + clientMarket + '\'' +
                ", deviceMac='" + deviceMac + '\'' +
                ", userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                ", ip='" + ip + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
