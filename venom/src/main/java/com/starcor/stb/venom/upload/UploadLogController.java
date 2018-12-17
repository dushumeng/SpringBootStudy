package com.starcor.stb.venom.upload;

import com.starcor.stb.venom.mvc.BaseApiController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/clientlog")
public class UploadLogController extends BaseApiController {

    @Resource
    private ClientLogService service;

    @RequestMapping(value = "/list")
    public String index() {
        return "redirect:/list/1";
    }

    @RequestMapping(value = "/list/{pageNumber}", method = RequestMethod.GET)
    public String list(@PathVariable Integer pageNumber, Model model) {
        // TODO: 2018/12/17 实现分页展示 
        return "";
    }

}
