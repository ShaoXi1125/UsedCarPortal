package com.example.usedcarportal.service;

import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllActiveCars() {
        return carRepository.findByStatusTrue();
    }
}
