package com.starcor.stb.venom.api;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class ApiHeader implements Serializable{

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

    public String getClientOS() {
        return clientOS;
    }

    public void setClientOS(String clientOS) {
        this.clientOS = clientOS;
    }

    public String getClientOSVersion() {
        return clientOSVersion;
    }

    public void setClientOSVersion(String clientOSVersion) {
        this.clientOSVersion = clientOSVersion;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getClientVersionName() {
        return clientVersionName;
    }

    public void setClientVersionName(String clientVersionName) {
        this.clientVersionName = clientVersionName;
    }

    public String getClientVersionCode() {
        return clientVersionCode;
    }

    public void setClientVersionCode(String clientVersionCode) {
        this.clientVersionCode = clientVersionCode;
    }

    public String getDeviceUUID() {
        return deviceUUID;
    }

    public void setDeviceUUID(String deviceUUID) {
        this.deviceUUID = deviceUUID;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getClientMarket() {
        return clientMarket;
    }

    public void setClientMarket(String clientMarket) {
        this.clientMarket = clientMarket;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "ApiHeader{" +
                "clientOS='" + clientOS + '\'' +
                ", clientOSVersion='" + clientOSVersion + '\'' +
                ", clientVersion='" + clientVersion + '\'' +
                ", clientVersionName='" + clientVersionName + '\'' +
                ", clientVersionCode='" + clientVersionCode + '\'' +
                ", deviceUUID='" + deviceUUID + '\'' +
                ", deviceModel='" + deviceModel + '\'' +
                ", deviceBrand='" + deviceBrand + '\'' +
                ", clientMarket='" + clientMarket + '\'' +
                ", deviceMac='" + deviceMac + '\'' +
                ", userId='" + userId + '\'' +
                ", timestamp=" + timestamp +
                ", sign='" + sign + '\'' +
                '}';
    }
}
