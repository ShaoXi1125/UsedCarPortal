package com.example.usedcarportal.controller;

import com.example.usedcarportal.entity.Appointment;
import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.service.AppointmentService;
import com.example.usedcarportal.service.CarService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("car/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CarService carService;

    @GetMapping("/book/{carId}")
    public String showAppointmentForm(@PathVariable Long carId, Model model) {
        Car car = carService.getCarById(carId);
        if (car == null) {
            return "redirect:/404";
        }
        model.addAttribute("car", car);
        return "appointment";
    }

    @PostMapping("/book")
    public String bookAppointment(HttpSession session,
            @RequestParam Long carId,
            @RequestParam String dateTime,
            @RequestParam(required = false) String notes,
            RedirectAttributes redirectAttributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Login First！");
            return "redirect:/login";
        }

        try {
            LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime);
            appointmentService.createAppointment(user.getId(), carId, parsedDateTime, notes);
            redirectAttributes.addFlashAttribute("message", "Appointment has been submitted！");
            return "redirect:/car/" + carId;
        } catch (DateTimeParseException e) {
            redirectAttributes.addFlashAttribute("error", "Invalid Date！");
            return "redirect:/book";
        }
    }

    // 获取某辆车的预约列表
    @GetMapping("/car/{carId}")
    public List<Appointment> getCarAppointments(@PathVariable Long carId) {
        return appointmentService.getAppointmentsForCar(carId);
    }

    @GetMapping("/my_appointments")

    public String userAppointments(Model model, HttpSession session,RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Login First！");
            return "redirect:/login";
        }
        
        String username = user.getUsername();
        List<Appointment> appointments = appointmentService.getUserAppointments(username);
        model.addAttribute("appointments", appointments);
        
        return "my_appointments";  // 确保和 my_appointments.jsp 匹配
    }



}
