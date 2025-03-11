package com.example.usedcarportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cars")
@Getter
@Setter

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private double price;

    @Column(length = 1000)
    private String description;

    @Column
    private String imageUrl; // 图片链接

    @Column(nullable = false)
    private boolean status = true; // true = 在售, false = 已下架

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner; // 车主
}
