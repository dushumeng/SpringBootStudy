package com.starcor.stb.venom.helper;

import com.starcor.stb.core.util.DateUtils;
import com.starcor.stb.core.util.FileUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class UploadHelper {

    private static final String CLIENT_LOG_PATH = "/clientLog";

    @Value("${com.starcor.stb.upload.path}")
    private String uploadPath;

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
        return uploadPath + CLIENT_LOG_PATH + "/" + fileName;
    }
}
