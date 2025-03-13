package com.example.usedcarportal.controller;

import com.example.usedcarportal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        return "cars";
    }

    // @GetMapping("/search")
    // public String searchCars(@RequestParam(required = false) String make,
    //         @RequestParam(required = false) String model,
    //         @RequestParam(required = false) Integer year,
    //         @RequestParam(required = false) Double minPrice,
    //         @RequestParam(required = false) Double maxPrice,
    //         Model model) {
    //     model.addAttribute("carList", carService.searchCars(make, model, year, minPrice, maxPrice));
    //     return "cars";
    // }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

}