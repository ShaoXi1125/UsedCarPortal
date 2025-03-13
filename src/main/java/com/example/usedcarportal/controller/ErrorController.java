package com.example.usedcarportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/403")
    public String show403() {
        return "403"; // 返回 403.jsp
    }

    @GetMapping("/404")
    public String show404() {
        return "404"; // 返回 404.jsp
    }
}