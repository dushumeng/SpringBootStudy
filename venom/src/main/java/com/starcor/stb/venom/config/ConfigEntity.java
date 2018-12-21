package com.starcor.stb.venom.config;

import com.starcor.stb.core.util.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigEntity {

    @Value("${com.starcor.stb.upload.path}")
    private String uploadPath;

    @Value("${com.starcor.stb.upload.path.size}")
    private String midUploadPathSize;

    @Value("${com.starcor.stb.api.md5.key}")
    private String md5Key;

    @Value("${com.starcor.stb.upload.file.size}")
    private String midUploadFileSize;

    private long uploadPathSize = 0;

    private long uploadFileSize = 0;

    public String getUploadPath() {
        return uploadPath;
    }

    public String getMd5Key() {
        return md5Key;
    }

    public long getUploadPathSize() {
        if (uploadPathSize == 0) {
            uploadPathSize = convertFileSize(midUploadPathSize, 20 * 1024 * 1024);
        }
        return uploadPathSize;
    }

    public long getUploadFileSize() {
        if (uploadFileSize == 0) {
            uploadFileSize = convertFileSize(midUploadFileSize, 1 * 1024 * 1024);
        }
        return uploadFileSize;
    }

    private long convertFileSize(String size, long defaultValue) {
        if (StringUtils.isBlank(size)) {
            return defaultValue;
        }
        String midSize = size.toLowerCase();
        if (midSize.endsWith("mb")) {
            long num = NumberUtils.parseLong(midSize.substring(0, midSize.length() - "mb".length())) * 1024 * 1024;
            return num == 0 ? defaultValue : num;
        } else if (midSize.endsWith("kb")) {
            long num = NumberUtils.parseLong(midSize.substring(0, midSize.length() - "kb".length())) * 1024;
            return num == 0 ? defaultValue : num;
        } else {
            return defaultValue;
        }
    }
}
