package com.example.usedcarportal.controller;

import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // 返回 login.jsp
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password,
            HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user); // 登录成功，保存用户到 session
            return "redirect:/"; // 重定向到首页
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password!"); // 登录失败，显示错误
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 退出登录，清除 session
        return "redirect:/login";
    }
}