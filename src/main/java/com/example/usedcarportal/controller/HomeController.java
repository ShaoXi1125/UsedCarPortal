package com.example.usedcarportal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Should resolve to index.html or your home template
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Should resolve to login.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // Should resolve to dashboard.html
    }
}