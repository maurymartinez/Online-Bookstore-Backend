package com.quasas.bookstore.security.infrastructure.service;

import com.quasas.bookstore.security.application.service.AuthService;
import com.quasas.bookstore.security.application.service.JwtService;
import com.quasas.bookstore.security.application.service.PasswordEncoder;
import com.quasas.bookstore.security.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServiceAdapter {

    @Bean
    public AuthService authService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        return new AuthService(userRepository, passwordEncoder, jwtService);
    }
}
