package com.starcor.stb.core.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static boolean transferFile(MultipartFile file, File localFile) {
        if (file.isEmpty()) {
            return false;
        }
        localFile.getParentFile().mkdirs();
        try {
            localFile.deleteOnExit();
            localFile.createNewFile();
            file.transferTo(localFile);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String filePath) {
        try {
            File file = new File(filePath);
            file.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static long sizeOfDirectory(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return 0;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return 0;
        }
        long size = org.apache.commons.io.FileUtils.sizeOfDirectory(file);
        return size;
    }
}
