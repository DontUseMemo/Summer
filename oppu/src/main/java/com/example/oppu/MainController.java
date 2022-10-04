package com.example.oppu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/admin")
    public String login() {
        return "/admin/login";
    }
}