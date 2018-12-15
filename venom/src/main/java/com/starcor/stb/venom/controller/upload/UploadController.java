package com.starcor.stb.venom.controller.upload;

import com.starcor.stb.venom.api.ApiHeader;
import com.starcor.stb.venom.api.ApiHeaderVo;
import com.starcor.stb.venom.service.JournalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UploadController {

    private JournalService journalService;

    public JournalService getJournalService() {
        return journalService;
    }

    @Resource(name = "journalService")
    public void setJournalService(JournalService journalService) {
        this.journalService = journalService;
    }


    @RequestMapping(value = "/function")//,method = RequestMethod.POST
    @ResponseBody
    public ResponseData function(@RequestParam("clientOS") String os){//@RequestParam() ApiHeader apiHeader
        ResponseData resultData = new ResponseData();

        String name = "fileName.zip";// TODO: 2018/12/15 这里是上传文件的名称
//        ApiHeaderVo apiHeaderVo = new ApiHeaderVo();
//        boolean copySucceed = apiHeaderVo.copyFromApiHeader(apiHeader);
//        apiHeaderVo.setJournalPath(name);
//
//        if (!copySucceed){
//            return resultData.failure("数据拷贝错误");
//        }
//
//        boolean insertSucceed = journalService.insertJournalInfo(apiHeaderVo);
//        if (!insertSucceed){
//            return resultData.failure("插入数据库错误");
//        }
        ResponseData succeed = resultData.succeed();
        return succeed;
    }

}
