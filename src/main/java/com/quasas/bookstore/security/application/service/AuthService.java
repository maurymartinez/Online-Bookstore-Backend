package com.quasas.bookstore.security.application.service;

import com.quasas.bookstore.security.domain.User;
import com.quasas.bookstore.security.domain.UserRepository;
import com.quasas.bookstore.security.domain.valueobject.Email;
import com.quasas.bookstore.security.domain.valueobject.PasswordHash;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String rawEmail, String rawPassword, String name) {
        Email email = new Email(rawEmail);

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }

        String hashed = passwordEncoder.encode(rawPassword);

        User user = new User(
                UUID.randomUUID(),
                email,
                name,
                new PasswordHash(hashed)
        );

        userRepository.save(user);
    }
}
