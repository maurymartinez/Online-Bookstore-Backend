package com.quasas.bookstore.security.application.service;

import com.quasas.bookstore.security.domain.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String email, String password, String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
