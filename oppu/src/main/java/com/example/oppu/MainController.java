package com.example.oppu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    //메인 홈페이지
    @RequestMapping("/")
    public String root() {
        return "/index";
    }

}