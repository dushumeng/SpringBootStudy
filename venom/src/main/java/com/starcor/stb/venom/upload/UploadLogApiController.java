package com.starcor.stb.venom.upload;

import com.starcor.stb.venom.api.ApiHeader;
import com.starcor.stb.venom.api.ApiResponse;
import com.starcor.stb.venom.mvc.BaseApiController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
public class UploadLogApiController extends BaseApiController {

    @Resource
    private ClientLogService service;

    @Value("${com.starcor.stb.max-file-size}")
    private int maxFileSize;

    @ResponseBody
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public Object upload(HttpServletRequest request, @RequestParam Map<String, Object> params, @RequestParam("file") MultipartFile file) {

        ApiHeader apiHeader = (ApiHeader) request.getAttribute("api_header");
        if (apiHeader == null) {
            return wrapData(ApiResponse.CODE.SIGN_ERROR, "api header is null");
        }
        if (file == null) {
            return wrapData(ApiResponse.CODE.FAIL, "file is null");
        }
        long size = file.getSize();
        if (size > maxFileSize) {
            return wrapData(ApiResponse.CODE.FAIL, "file size is to big");
        }

        // TODO: 2018/12/17 文件校验判断是否是ZIP文件
        // TODO: 2018/12/17 获取zip文件中的日志文件名称，生成password
        // TODO: 2018/12/17 考虑异步保存文件，将数据存入数据库

        return wrapData(ApiResponse.CODE.SUCCESS);
    }

    private String getJarRootPath() {
        try {
            String path = ResourceUtils.getURL("classpath:").getPath();
            File rootFile = new File(path);
            if (!rootFile.exists()) {
                rootFile = new File("");
            }
            return rootFile.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
