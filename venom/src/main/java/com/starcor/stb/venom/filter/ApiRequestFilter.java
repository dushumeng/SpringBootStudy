package com.starcor.stb.venom.filter;

import com.google.gson.Gson;
import com.starcor.stb.core.util.EncryptUtils;
import com.starcor.stb.venom.api.ApiHeader;
import com.starcor.stb.venom.api.ApiResponse;
import com.starcor.stb.venom.config.ConfigEntity;
import com.starcor.stb.venom.log.Logger;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

@Component
public class ApiRequestFilter extends HandlerInterceptorAdapter {

    @Autowired
    private Gson gson;

    @Autowired
    private ConfigEntity configEntity;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String servletPath = request.getServletPath();
        Logger.i("ApiRequestFilter--->preHandle", servletPath);
        if (StringUtils.isEmpty(servletPath)) {
            return true;
        }
        if (servletPath.startsWith("/api/")) {
            ApiHeader apiHeader = ApiHeader.parse(request);
            Logger.i("ApiRequestFilter--->preHandle", apiHeader.toString());
            if (!apiHeader.isValid()) {
                response.setContentType("application/json");
                response.getOutputStream().write("{\"code\":-3,\"msg\":\"request is not valid\"}".getBytes());
                return false;
            }
            String midSign = EncryptUtils.md5(apiHeader.timestamp + configEntity.getMd5Key());
            if (!StringUtils.equals(midSign, apiHeader.sign)) {
                response.setContentType("application/json");
                response.getOutputStream().write("{\"code\":-1}".getBytes());
                return false;
            }
            if (request instanceof MultipartHttpServletRequest) {
                if (!checkMultipartFile((MultipartHttpServletRequest) request, response)) {
                    return false;
                }
            }
            request.setAttribute("api_header", apiHeader);
        } else if (servletPath.startsWith("/apiold/")) {
            if (!checkMultipartFile((MultipartHttpServletRequest) request, response)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String servletPath = request.getServletPath();
        Logger.i("ApiRequestFilter--->postHandle", servletPath);
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String servletPath = request.getServletPath();
        Logger.i("ApiRequestFilter--->afterCompletion", servletPath);
        super.afterCompletion(request, response, handler, ex);
    }

    private boolean checkMultipartFile(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
        Iterator<String> fileNames = multipartRequest.getFileNames();
        if (fileNames == null) {
            return true;
        }
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            List<MultipartFile> files = multipartRequest.getFiles(fileName);
            if (files == null || files.size() == 0) {
                continue;
            }
            for (MultipartFile file : files) {
                String name = file.getOriginalFilename();
                String contentType = file.getContentType();
                long size = file.getSize();
                Logger.i("check file name=", name, ",contentType=", contentType, ";size=", String.valueOf(size));

                if (size > configEntity.getUploadClientLogFileSize()) {
                    ApiResponse apiResponse = new ApiResponse(ApiResponse.CODE.FAIL.value, "file size is to big");
                    response.setContentType("application/json");
                    response.getOutputStream().write(gson.toJson(apiResponse).getBytes());
                    return false;
                }
            }
        }
        return true;
    }
}
