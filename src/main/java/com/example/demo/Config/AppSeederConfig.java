package com.example.demo.Config;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppSeederConfig {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner seedSupervisorUser() {
        return args -> {
            if (userRepo.findByEmail("supervisor@company.com").isEmpty()) {
                User supervisor = new User();
                supervisor.setUsername("Lerato");
                supervisor.setSurname("Mokoena");
                supervisor.setEmail("supervisor@company.com");
                supervisor.setPassword(passwordEncoder.encode("supersecure"));
                supervisor.setRole(Role.SUPERVISOR);

                userRepo.save(supervisor);


            }
        };
    }
}
