package com.example.usedcarportal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','ADMIN') NOT NULL")
    private Role role = Role.USER; // 默认普通用户

    @CreationTimestamp // 自动填充创建时间
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // 自动填充更新时间
    private LocalDateTime updatedAt;
}
