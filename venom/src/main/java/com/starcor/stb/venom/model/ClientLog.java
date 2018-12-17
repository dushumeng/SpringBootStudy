package com.starcor.stb.venom.model;

import java.io.Serializable;

public class ClientLog implements Serializable {
    private Integer id;

    private String clientOs;

    private String clientOsVersion;

    private String clientVersion;

    private String clientVersionName;

    private String clientVersionCode;

    private String deviceUuid;

    private String deviceModel;

    private String deviceBrand;

    private String clientMarket;

    private String deviceMac;

    private String userId;

    private String ip;

    private Integer createTime;

    private String type;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientOs() {
        return clientOs;
    }

    public void setClientOs(String clientOs) {
        this.clientOs = clientOs;
    }

    public String getClientOsVersion() {
        return clientOsVersion;
    }

    public void setClientOsVersion(String clientOsVersion) {
        this.clientOsVersion = clientOsVersion;
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

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}