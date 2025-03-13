package com.example.usedcarportal.controller;

import com.example.usedcarportal.entity.Role;
import com.example.usedcarportal.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return "redirect:/403";
        }
        model.addAttribute("admin", currentUser);
        return "admin/dashboard";
    }
}