package com.starcor.stb.venom.upload;

import com.starcor.stb.core.util.EncryptUtils;
import com.starcor.stb.core.util.FileUtils;
import com.starcor.stb.venom.api.ApiResponse;
import com.starcor.stb.venom.helper.UploadHelper;
import com.starcor.stb.venom.model.ClientLog;
import com.starcor.stb.venom.mvc.BaseService;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class ClientLogService extends BaseService<ClientLog> {

    @Autowired
    private UploadHelper uploadHelper;

    @Override
    protected String namespace() {
        return "com.starcor.stb.venom.mapper.ClientLogMapper";
    }

    public List<ClientLog> listAll() {
        return mybatisService.findList(statement("listAll"), null);
    }

    public ApiResponse save(ClientLog clientLog, MultipartFile file) {
        File midFile = uploadHelper.uploadClientLog(file);
        if (midFile == null) {
            return ApiResponse.createError("保存文件失败!!!");
        }
        String fileName = midFile.getName();
        clientLog.setFilePath(fileName);
        String midFileName = null;
        try {
            ZipFile zipFile = new ZipFile(midFile.getPath());
            zipFile.setFileNameCharset("UTF-8");
            List fileHeaders = zipFile.getFileHeaders();
            if (fileHeaders != null && fileHeaders.size() > 0) {
                for (int i = 0; i < fileHeaders.size(); i++) {
                    FileHeader fileHeader = (FileHeader) fileHeaders.get(i);
                    String zipEntryName = fileHeader.getFileName();
                    if (zipEntryName.endsWith("_logCat.txt")) {
                        midFileName = zipEntryName.substring(0, zipEntryName.length() - "_logCat.txt".length());
                        break;
                    } else if (zipEntryName.endsWith("_selfChecking.txt")) {
                        midFileName = zipEntryName.substring(0, zipEntryName.length() - "_selfChecking.txt".length());
                        break;
                    } else if (zipEntryName.endsWith(".txt")) {
                        midFileName = zipEntryName.substring(0, zipEntryName.length() - ".txt".length());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(midFileName)) {
            FileUtils.delete(midFile.getPath());
            return ApiResponse.createError("zip文件解析失败!!!");
        }
        clientLog.setPsd(EncryptUtils.md5(midFileName + "starcor"));
        insert(clientLog);
        return ApiResponse.createSuccess("保存文件成功!!!", clientLog);
    }

    public boolean delete(List<ClientLog> clientLogList) {
        if (clientLogList == null || clientLogList.size() == 0) {
            return false;
        }
        for (ClientLog clientLog : clientLogList) {
            String file = uploadHelper.getClientLogPath(clientLog.getFilePath());
            FileUtils.delete(file);
            delete(clientLog.getId());
        }
        return true;
    }
}
