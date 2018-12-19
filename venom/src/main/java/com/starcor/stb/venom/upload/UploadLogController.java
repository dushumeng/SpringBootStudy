package com.starcor.stb.venom.upload;

import com.starcor.stb.core.pager.Pager;
import com.starcor.stb.venom.model.ClientLog;
import com.starcor.stb.venom.mvc.BaseApiController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/clientlog")
public class UploadLogController extends BaseApiController {

    @Resource
    private ClientLogService service;

    @RequestMapping(value = "/list")
    public String list(Model model) {
//        Pager pager = service.findPage(bean);
        List<ClientLog> clientLogs = service.listAll();
        model.addAttribute("list", clientLogs);
        return "/clientlog/list";
    }

    @RequestMapping(value = "/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        ClientLog clientLog = service.findById(id);
        model.addAttribute("info", clientLog);
        return "clientlog/show";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        int delete = service.delete(id);
        return list(model);
    }
}
