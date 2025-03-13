package com.example.usedcarportal.service;

import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Value("${upload.dir}")
    private String uploadDir; // 从 application.properties 读取上传目录

    public List<Car> getAllActiveCars() {
        return carRepository.findByStatusTrue();
    }

    public Car saveCar(Car car, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs(); // 确保目录存在
            }
            Files.copy(file.getInputStream(), Paths.get(uploadDir + filename));
            car.setImageUrl("/uploads/" + filename);
        }
        return carRepository.save(car);
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
