package com.starcor.stb.venom.upload;


import com.starcor.stb.venom.api.ApiHeader;
import com.starcor.stb.venom.controller.BaseController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UploadLogController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "/api/upload", method = RequestMethod.POST)
    public Object upload(HttpServletRequest request, @RequestParam(name = "type") String type, @RequestParam("file") MultipartFile file) {
        ApiHeader apiHeader = ApiHeader.parse(request);

        long size = file.getSize();


        return wrapData("");
    }

}
