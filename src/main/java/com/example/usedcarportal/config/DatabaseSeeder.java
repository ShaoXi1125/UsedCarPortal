package com.example.usedcarportal.config;

// import com.example.usedcarportal.entity.Car;
import com.example.usedcarportal.entity.Role;
import com.example.usedcarportal.entity.User;
import com.example.usedcarportal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

// import java.math.BigDecimal;
import java.time.LocalDateTime;
// import java.util.List;

@Configuration
public class DatabaseSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setCreatedAt(LocalDateTime.now());
                userRepository.save(admin);

                User user = new User();
                user.setUsername("user");
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setRole(Role.USER);
                user.setCreatedAt(LocalDateTime.now());
                userRepository.save(user);
            }

        };
    }
}
