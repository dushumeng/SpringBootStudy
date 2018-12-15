package com.starcor.stb.venom.api;

import java.io.File;
import java.util.logging.Logger;

/**
 * Created by chongyang.gao on 2018/12/15.
 */
public class ApiHeaderVo extends ApiHeader {

    private static final Logger logger = Logger.getLogger("");

    public static final String JOURNAL_DIR = "/";

    static {
        File file = new File(JOURNAL_DIR);
        if (!file.exists() || !file.isDirectory()) {
            boolean mkdirs = file.mkdirs();
            logger.info("ApiHeaderVo, mkdirs = " + mkdirs);
        }
    }

    //journalPath的值设置为 JOURNAL_DIR+文件名
    private String journalPath;

    public String getJournalPath() {
        return journalPath;
    }

    public void setJournalPath(String journalName) {
        if (journalName != null && !"".equals(journalName)) {
            this.journalPath = JOURNAL_DIR + File.separator + journalName;
        }
    }

    public boolean copyFromApiHeader(ApiHeader apiHeader) {
        if (apiHeader == null) {
            return false;
        }
        this.clientMarket = apiHeader.clientMarket;
        this.clientMarket = apiHeader.clientOS;
        this.clientMarket = apiHeader.clientOSVersion;
        this.clientMarket = apiHeader.clientVersion;
        this.clientMarket = apiHeader.clientVersionCode;
        this.clientMarket = apiHeader.clientVersionName;
        this.clientMarket = apiHeader.deviceBrand;
        this.clientMarket = apiHeader.deviceMac;
        this.clientMarket = apiHeader.deviceModel;
        this.clientMarket = apiHeader.deviceUUID;
        this.clientMarket = apiHeader.sign;
        this.clientMarket = apiHeader.userId;
        return true;
    }

    @Override
    public String toString() {
        return "ApiHeaderVo{" +
                ", clientOS='" + clientOS + '\'' +
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
                "journalPath='" + journalPath + '\'' +
                '}';
    }
}
