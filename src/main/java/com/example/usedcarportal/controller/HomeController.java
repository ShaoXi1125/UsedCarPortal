package com.example.usedcarportal.controller;

import com.example.usedcarportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("carList", carService.getAllActiveCars());
        return "index";
    }

    @GetMapping("/cars")
    public String cars(Model model) {
        model.addAttribute("carList", carService.getAllActiveCars());
        return "cars"; // 假设有一个 cars.jsp，如果没有可以直接复用 index.jsp
    }

}