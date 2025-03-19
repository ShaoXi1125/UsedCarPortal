package com.example.usedcarportal.service;

import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.User;
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
import java.util.Optional;
import org.springframework.lang.Nullable;


@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Value("${upload.dir}")
    private String uploadDir;

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

    public boolean deactivateCar(Long carId, User user) {
        Optional<Car> carOpt = carRepository.findById(carId);
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            // 只有车主本人才能下架车辆
            if (car.getOwner().getId().equals(user.getId())) {
                car.setStatus(false); // 设为下架
                carRepository.save(car);
                return true;
            }
        }
        return false;
    }

    public boolean reactivateCar(Long id, User user) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null || !car.getOwner().equals(user)) {
            return false;
        }
        car.setStatus(true); // 重新上架
        carRepository.save(car);
        return true;
    }

    public boolean updateCar(Long carId, String make, String model, int year, double price, String description,
            MultipartFile image, User user) {
        Optional<Car> carOpt = carRepository.findById(carId);
        if (carOpt.isPresent()) {
            Car car = carOpt.get();
            // 只有车主本人才能编辑车辆
            if (!car.getOwner().getId().equals(user.getId())) {
                return false;
            }

            car.setMake(make);
            car.setModel(model);
            car.setYear(year);
            car.setPrice(price);
            car.setDescription(description);

            // 处理新图片
            if (image != null && !image.isEmpty()) {
                try {
                    String filename = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                    File uploadPath = new File(uploadDir);
                    if (!uploadPath.exists()) {
                        uploadPath.mkdirs();
                    }
                    Files.copy(image.getInputStream(), Paths.get(uploadDir + filename));
                    car.setImageUrl("/uploads/" + filename);
                } catch (IOException e) {
                    return false;
                }
            }

            carRepository.save(car);
            return true;
        }
        return false;
    }

    public List<Car> getCarsByUser(User user) {
        return carRepository.findByOwner(user);
    }

    public List<Car> searchCars(@Nullable String make, @Nullable String model, @Nullable Integer year, @Nullable Double minPrice, @Nullable Double maxPrice) {
        return carRepository.searchCars(
            make == null || make.trim().isEmpty() ? null : make,
            model == null || model.trim().isEmpty() ? null : model,
            year == null || year == 0 ? null : year,
            minPrice == null || minPrice == 0 ? null : minPrice,
            maxPrice == null || maxPrice == 0 ? null : maxPrice
        );
    }


    public List<Car> getAllCars() {
        return carRepository.findAll(); // 获取所有车辆
    }

    public boolean deactivateCarByAdmin(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return false;
        }
        car.setStatus(false);
        carRepository.save(car);
        return true;
    }

    public boolean reactivateCarByAdmin(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return false;
        }
        car.setStatus(true);
        carRepository.save(car);
        return true;
    }
    

}
