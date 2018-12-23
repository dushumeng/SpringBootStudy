package com.starcor.stb.venom;

import com.starcor.stb.venom.upload.ClientLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @Autowired
    private ClientLogService clientLogService;

    @RequestMapping("")
    public String index(Model model) {
//        long count = clientLogService.count();
        model.addAttribute("clientLogSize", 999);
        return "dashboard/index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("logout")
    public String logout() {
        return "login";
    }
}
