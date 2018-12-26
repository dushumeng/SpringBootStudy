package com.starcor.stb.venom.upload;

import com.starcor.stb.core.util.EncryptUtils;
import com.starcor.stb.core.util.FileUtils;
import com.starcor.stb.venom.api.ApiResponse;
import com.starcor.stb.venom.config.ConfigEntity;
import com.starcor.stb.venom.helper.UploadHelper;
import com.starcor.stb.venom.log.Logger;
import com.starcor.stb.venom.model.ClientLog;
import com.starcor.stb.venom.mvc.BaseService;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.FileHeader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientLogService extends BaseService<ClientLog> {

    @Autowired
    private UploadHelper uploadHelper;

    @Autowired
    private ConfigEntity configEntity;

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
        List<Long> idList = new ArrayList<>();
        for (ClientLog clientLog : clientLogList) {
            String file = uploadHelper.getClientLogPath(clientLog.getFilePath());
            FileUtils.delete(file);
            idList.add(clientLog.getId());
        }
        mybatisService.delete("deleteByIds", idList);
        return true;
    }

    public void checkClientLogFileSize() {
        long size = FileUtils.sizeOfDirectory(uploadHelper.getClientLogDir());
        long uploadClientLogPathSize = configEntity.getUploadClientLogPathSize();
        Logger.i("checkClientLogFileSize--->current:", FileUtils.printSize(size), ",max:" + FileUtils.printSize(uploadClientLogPathSize));
        if (size < uploadClientLogPathSize) {
            return;
        }
        List<ClientLog> list = mybatisService.findList("listAll");
        List<ClientLog> midList = new ArrayList<>();
        int fileSize = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (fileSize > size / 4) {
                break;
            }
            ClientLog clientLog = list.get(i);
            String clientLogPath = uploadHelper.getClientLogPath(clientLog.getFilePath());
            File file = new File(clientLogPath);
            if (file.exists()) {
                fileSize += file.length();
            }
            midList.add(clientLog);
        }
        delete(midList);
    }

    public void removeOvertimeClientLog() {
        long checkTime = System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000;
        List<ClientLog> list = mybatisService.findList("listByTime", checkTime);
        Logger.i("removeOvertimeClientLog--->", (list == null || list.size() == 0 ? String.valueOf(0) : String.valueOf(list.size())));
        delete(list);
    }

    public boolean download(String fileName, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(new File(uploadHelper.getClientLogPath(fileName)));
            outputStream = response.getOutputStream();
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);   // 设置文件名
            //把输入流copy到输出流
            IOUtils.copy(inputStream, outputStream);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            inputStream = null;
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputStream = null;
        }

        return false;
    }

    public long count() {
        return mybatisService.count("", null);
    }
}
