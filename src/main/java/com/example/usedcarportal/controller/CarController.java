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
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/car/sell")
    public String showSellCarForm(HttpSession session,RedirectAttributes redirectAttributes) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        
        return "carsell"; // 对应前端页面
    }

    @GetMapping("")
    public String showCars(Model model) {
        List<Car> carList = carService.getAllActiveCars();
        model.addAttribute("carList", carList);
        return "cars"; // 对应 cars.jsp
    }

    @GetMapping("/search")
    public String searchCars(
        @RequestParam(required = false) String make,
        @RequestParam(required = false) String model,
        @RequestParam(required = false) Integer year,
        @RequestParam(required = false) Double minPrice,
        @RequestParam(required = false) Double maxPrice,
        Model modelAttr
    ) {
        List<Car> carList = carService.searchCars(make, model, year, minPrice, maxPrice);
        modelAttr.addAttribute("carList", carList);
        return "cars"; // 仍然返回 `cars.jsp`
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

    @GetMapping("/car/deactivate/{id}")
    public String deactivateCar(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Login firstfirst！");
            return "redirect:/login";
        }

        boolean success = carService.deactivateCar(id, currentUser);
        if (success) {
            redirectAttributes.addFlashAttribute("success",
                    "The vehicle has been successfully removed from the shelves!");
        } else {
            redirectAttributes.addFlashAttribute("error", "The vehicle cannot be removed from the shelves!");
        }

        return "redirect:/my_cars";
    }

    @GetMapping("/car/reactivate/{id}")
    public String reactivateCar(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Login first！");
            return "redirect:/login";
        }

        boolean success = carService.reactivateCar(id, currentUser);
        if (success) {
            redirectAttributes.addFlashAttribute("success", "The vehicle has been successfully relisted!");
        } else {
            redirectAttributes.addFlashAttribute("error", "The vehicle cannot be relisted!");
        }

        return "redirect:/my_cars";
    }


    @GetMapping("/car/edit/{id}")
    public String editCar(@PathVariable Long id, HttpSession session, Model model,
            RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Login first!");
            return "redirect:/login";
        }

        Car car = carService.getCarById(id);
        if (car == null || !car.getOwner().getId().equals(currentUser.getId())) {
            redirectAttributes.addFlashAttribute("error", "You are not authorized to edit this car!");
            return "redirect:/my_cars";
        }

        model.addAttribute("car", car);
        return "edit_car"; // 对应 `edit_car.jsp`
    }

    @PostMapping("/car/update")
    public String updateCar(
            @RequestParam Long id,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam int year,
            @RequestParam double price,
            @RequestParam String description,
            @RequestParam(value = "image", required = false) MultipartFile image,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Login first!");
            return "redirect:/login";
        }

        boolean success = carService.updateCar(id, make, model, year, price, description, image, currentUser);
        if (success) {
            redirectAttributes.addFlashAttribute("success", "Car updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update the car!");
        }

        return "redirect:/my_cars";
    }

    @GetMapping("/my_cars")
    public String viewMyCars(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) {
            redirectAttributes.addFlashAttribute("error", "Login first!");
            return "redirect:/login";
        }

        System.out.println("Current User: " + currentUser); // Debug
        System.out.println("User ID: " + currentUser.getId());

        List<Car> myCars = carService.getCarsByUser(currentUser);
        System.out.println("Cars found: " + myCars.size()); // Debug

        model.addAttribute("myCars", myCars);
        return "my_cars";
    }

    

}
