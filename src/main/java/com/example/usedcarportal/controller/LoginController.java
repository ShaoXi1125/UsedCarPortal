package com.example.usedcarportal.controller;

import com.example.usedcarportal.entity.Role;
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
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password,
            HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);

            if (user.getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/dashboard";
            } else if (user.getRole().equals(Role.USER)) {
                return "redirect:/";
            } else {
                return "redirect:/403";
            }

        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password!");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}