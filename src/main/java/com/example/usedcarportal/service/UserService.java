package com.example.usedcarportal.service;

import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.entity.Role;
import com.example.usedcarportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 登录验证
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    // 注册用户
    public User register(String username, String email, String password, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        // if (userRepository.findByEmail(email).isPresent()) {
        // throw new IllegalArgumentException("Email already exists");
        // }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // 加密存储
        user.setRole("ADMIN".equalsIgnoreCase(role) ? Role.ADMIN : Role.USER);

        return userRepository.save(user);
    }
}