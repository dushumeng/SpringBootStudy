package com.starcor.stb.venom.helper;

import com.starcor.stb.core.util.DateUtils;
import com.starcor.stb.core.util.FileUtils;
import com.starcor.stb.venom.config.ConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class UploadHelper {

    private static final String CLIENT_LOG_PATH = "/clientLog";

    @Autowired
    private ConfigEntity configEntity;

    public File uploadClientLog(MultipartFile multipartFile) {
        String fileName = getClientLogPath(DateUtils.parse(System.currentTimeMillis(), DateUtils.DATE_FORMAT_4) + "_" + UUID.randomUUID().toString() + ".zip");
        File clientLog = new File(fileName);
        boolean success = FileUtils.transferFile(multipartFile, clientLog);
        if (success) {
            return clientLog;
        } else {
            return null;
        }
    }

    public String getClientLogPath(String fileName) {
        return configEntity.getUploadPath() + CLIENT_LOG_PATH + "/" + fileName;
    }

    public String getClientLogDir() {
        return configEntity.getUploadPath() + CLIENT_LOG_PATH;
    }
}
