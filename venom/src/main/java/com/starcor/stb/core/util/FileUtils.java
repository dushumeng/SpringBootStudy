package com.starcor.stb.core.util;

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
            file.deleteOnExit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
