package com.example.usedcarportal.controller;

import com.example.usedcarportal.entity.Appointment;
import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.Role;
import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.service.UserService;
import com.example.usedcarportal.service.CarService;
import com.example.usedcarportal.service.AppointmentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/admin") // 统一前缀 "/admin"
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CarService carService;

    // 获取当前登录的管理员
    private User getCurrentAdmin(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null || currentUser.getRole() != Role.ADMIN) {
            return null;
        }
        return currentUser;
    }

    // **管理员 Dashboard**
    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, Model model) {
        User admin = getCurrentAdmin(session);
        if (admin == null) {
            return "redirect:/403";
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("admin", admin);
        model.addAttribute("users", users);
        return "admin/dashboard";
    }

    // **升级用户为管理员**
    @PostMapping("/set-admin")
    public String setUserAsAdmin(@RequestParam("userId") Long userId, HttpSession session, RedirectAttributes redirectAttributes) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        try {
            userService.setUserAsAdmin(userId);
            redirectAttributes.addFlashAttribute("successMessage", "User has been upgraded to Admin.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/dashboard";
    }

    // **查看预约**
    @GetMapping("/appointments")
    public String listAppointments(HttpSession session, Model model) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "admin/appointments";
    }

    // **审批预约**
    @PostMapping("/appointments/{id}/approve")
    public String approve(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        appointmentService.approveAppointment(id);
        redirectAttributes.addFlashAttribute("message", "Appointment approved!");
        return "redirect:/admin/appointments";
    }

    // **拒绝预约**
    @PostMapping("/appointments/{id}/deny")
    public String deny(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        appointmentService.denyAppointment(id);
        redirectAttributes.addFlashAttribute("error", "Appointment denied.");
        return "redirect:/admin/appointments";
    }

    // **查看所有车辆**
    @GetMapping("/cars")
    public String adminViewCars(HttpSession session, Model model) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        List<Car> allCars = carService.getAllCars();
        model.addAttribute("allCars", allCars);
        return "admin/admin_car_list"; // 对应 admin_car_list.jsp
    }

    // **管理员停用车辆**
    @GetMapping("/car/deactivate/{id}")
    public String deactivateCarByAdmin(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        boolean success = carService.deactivateCarByAdmin(id);
        if (success) {
            redirectAttributes.addFlashAttribute("success", "Car has been deactivated!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to deactivate car!");
        }

        return "redirect:/admin/cars";
    }

    // **管理员重新激活车辆**
    @GetMapping("/car/reactivate/{id}")
    public String reactivateCarByAdmin(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (getCurrentAdmin(session) == null) {
            return "redirect:/403";
        }

        boolean success = carService.reactivateCarByAdmin(id);
        if (success) {
            redirectAttributes.addFlashAttribute("success", "Car has been reactivated!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to reactivate car!");
        }

        return "redirect:/admin/cars";
    }

    @GetMapping("/cars/search")
    public String searchCars(
        @RequestParam(required = false) String make,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) Integer year,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        HttpSession session, 
        Model modelAttr
    ) {
        if (getCurrentAdmin(session) == null) { 
            return "redirect:/403";
        }
    
        List<Car> carList = carService.searchCars(make, model, year, minPrice, maxPrice);
        modelAttr.addAttribute("allCars", carList); // 统一 key
        return "admin/admin_car_list"; 
    }
    
      
}
