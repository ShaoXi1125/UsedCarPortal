package com.example.usedcarportal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller

public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", session.getAttribute("user"));
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(@RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null) {
            User updatedUser = userService.updateProfile(currentUser.getId(), email, password);
            session.setAttribute("user", updatedUser);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        }
        return "redirect:/profile";
    }
}
