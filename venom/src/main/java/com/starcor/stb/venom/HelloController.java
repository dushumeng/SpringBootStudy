package com.starcor.stb.venom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(Model m) {
        m.addAttribute("now", new Date().toString());
        return "hello";
    }

}
