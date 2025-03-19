package com.example.usedcarportal.repository;

import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatusTrue();

    List<Car> findByOwner(User owner);

    Optional<Car> findById(Long id);

    List<Car> findAll();

     @Query("SELECT c FROM Car c WHERE " +
           "(:make IS NULL OR LOWER(c.make) LIKE LOWER(CONCAT('%', :make, '%'))) AND " +
           "(:model IS NULL OR LOWER(c.model) LIKE LOWER(CONCAT('%', :model, '%'))) AND " +
           "(:year IS NULL OR c.year = :year) AND " +
           "(:minPrice IS NULL OR c.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR c.price <= :maxPrice) AND " +
           "c.status = true")
    List<Car> searchCars(
        @Param("make") String make,
        @Param("model") String model,
        @Param("year") Integer year,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice
    );
}
