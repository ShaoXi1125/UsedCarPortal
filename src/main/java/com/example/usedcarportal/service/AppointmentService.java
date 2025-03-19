package com.example.usedcarportal.service;

import com.example.usedcarportal.entity.Appointment;
import com.example.usedcarportal.entity.AppointmentStatus;

import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.repository.AppointmentRepository;
import com.example.usedcarportal.repository.CarRepository;
import com.example.usedcarportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    // 预约试驾
    public Appointment createAppointment(Long userId, Long carId, LocalDateTime dateTime, String notes) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Car car = carRepository.findById(carId)
            .orElseThrow(() -> new RuntimeException("Car not found"));

        // 检查是否已有预约
        List<Appointment> existingAppointments = appointmentRepository.findByCarIdAndAppointmentDateTimeAfter(carId, LocalDateTime.now());
        if (!existingAppointments.isEmpty()) {
            throw new RuntimeException("This car is already booked for a test drive.");
        }

        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setCar(car);
        appointment.setAppointmentDateTime(dateTime);
        appointment.setNotes(notes);

        return appointmentRepository.save(appointment);
    }

   
    public List<Appointment> getAppointmentsForCar(Long carId) {
        return appointmentRepository.findByCarIdAndAppointmentDateTimeAfter(carId, LocalDateTime.now());
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void approveAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(AppointmentStatus.APPROVED);
        appointmentRepository.save(appointment);
    }

    public void denyAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(AppointmentStatus.DENIED);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getUserAppointments(String username) {
        return appointmentRepository.findByUserUsername(username);
    }

}
