package com.starcor.stb.venom.upload;

import com.starcor.stb.venom.api.ApiHeader;
import com.starcor.stb.venom.api.ApiResponse;
import com.starcor.stb.venom.model.ClientLog;
import com.starcor.stb.venom.mvc.BaseApiController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UploadLogApiController extends BaseApiController {

    @Resource
    private ClientLogService service;

    @ResponseBody
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public Object upload(HttpServletRequest request, @RequestParam Map<String, String> params, @RequestParam("applog") MultipartFile file) {

        ApiHeader apiHeader = (ApiHeader) request.getAttribute("api_header");
        if (apiHeader == null) {
            return wrapData(ApiResponse.CODE.SIGN_ERROR, "api header is null");
        }
        if (file == null) {
            return wrapData(ApiResponse.CODE.FAIL, "file is null");
        }
        if (params == null) {
            return wrapData(ApiResponse.CODE.FAIL, "param is error");
        }

        ClientLog clientLog = new ClientLog();
        clientLog.setCreateTime(System.currentTimeMillis());
        clientLog.setClientOs(apiHeader.clientOS);
        clientLog.setClientMarket(apiHeader.clientMarket);
        clientLog.setClientOsVersion(apiHeader.clientOSVersion);
        clientLog.setClientOsFirmware(apiHeader.clientOSFirmware);
        clientLog.setAppVersionName(apiHeader.appVersionName);
        clientLog.setAppVersionCode(apiHeader.appVersionCode);
        clientLog.setDeviceUuid(apiHeader.deviceUUID);
        clientLog.setDeviceModel(apiHeader.deviceModel);
        clientLog.setDeviceMac(apiHeader.deviceMac);
        clientLog.setDeviceBrand(apiHeader.deviceBrand);
        clientLog.setUserId(apiHeader.userId);
        clientLog.setIp(apiHeader.ip);
        String type = (String) params.get("type");
        type = StringUtils.isBlank(type) ? "error" : type;
        clientLog.setType(type);

        ApiResponse save = service.save(clientLog, file);

        return save;
    }

    @ResponseBody
    @RequestMapping(value = "/apiold/upload", method = RequestMethod.POST)
    public Object uploadOld(HttpServletRequest request, @RequestParam Map<String, String> params, @RequestParam("file") MultipartFile file) {

        return null;
    }
}
