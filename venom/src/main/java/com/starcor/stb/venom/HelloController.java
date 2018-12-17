package com.starcor.stb.venom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "test";
    }

    @RequestMapping("/")
    public String index() {
        return "dashboard/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
