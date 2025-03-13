// package com.example.usedcarportal.controller;

// import com.example.usedcarportal.entity.Car;
// import com.example.usedcarportal.service.CarService;
// import jakarta.servlet.http.HttpSession;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @Controller
// public class CarController {

//     @Autowired
//     private CarService carService;

//     @GetMapping("/car/{id}")
//     public String carDetails(@PathVariable Long id, Model model, HttpSession session) {
//         if (session.getAttribute("user") == null) {
//             return "redirect:/login"; // 未登录，重定向到登录页面
//         }
//         // Car car = carService.getCarById(id);
//         if (car == null) {
//             return "error"; // 假设有一个 error.jsp
//         }
//         model.addAttribute("car", car);
//         return "car-details"; // 返回车辆详情页面
//     }
// }