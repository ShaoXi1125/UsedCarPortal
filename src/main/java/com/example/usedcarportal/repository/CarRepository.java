package com.example.usedcarportal.repository;

import com.example.usedcarportal.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatusTrue();

    Optional<Car> findById(Long id);
}
