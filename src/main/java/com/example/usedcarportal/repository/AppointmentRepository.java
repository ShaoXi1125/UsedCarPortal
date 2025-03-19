package com.example.usedcarportal.repository;

import com.example.usedcarportal.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByUserId(Long userId);

    List<Appointment> findByCarId(Long carId); // 新增方法

    List<Appointment> findByCarIdAndAppointmentDateTimeAfter(Long carId, LocalDateTime now);
    
    List<Appointment> findByUserUsername(String username);
}
