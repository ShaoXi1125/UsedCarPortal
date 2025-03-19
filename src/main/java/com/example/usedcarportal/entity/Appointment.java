package com.example.usedcarportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 谁预约的

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car; // 预约哪辆车

    @Column(nullable = true)
    private LocalDateTime appointmentDateTime; // 预约时间

    @Column(length = 255)
    private String notes;

    @Enumerated(EnumType.STRING) // 确保 status 存储为字符串
    @Column(length = 50, nullable = false)
    private AppointmentStatus status = AppointmentStatus.PENDING; // 设置默认值
}
