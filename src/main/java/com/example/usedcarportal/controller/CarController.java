package com.example.usedcarportal.controller;

import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.service.CarService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/car/sell")
    public String showSellCarForm() {
        return "carsell"; // 对应前端页面
    }

    @PostMapping("/car/sell")
    public String postCarForSale(@RequestParam String make,
            @RequestParam String model,
            @RequestParam int year,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam("image") MultipartFile image,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Car car = new Car();
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setPrice(price);
        car.setDescription(description);
        car.setOwner(user);
        car.setStatus(true);

        try {
            carService.saveCar(car, image);
            redirectAttributes.addFlashAttribute("success", "Car posted successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload image.");
        }

        return "redirect:/";
    }

     @GetMapping("/car/{id}")
    public String viewCarDetails(@PathVariable Long id, Model model) {
        Car car = carService.getCarById(id);
        if (car == null) {
            return "redirect:/404"; // 处理找不到的情况
        }
        model.addAttribute("car", car);
        return "car_detail"; // 显示 car_detail.html 页面
    }
}
