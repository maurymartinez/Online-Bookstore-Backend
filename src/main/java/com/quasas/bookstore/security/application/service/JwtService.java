package com.quasas.bookstore.security.application.service;

import com.quasas.bookstore.security.domain.User;

public interface JwtService {
    String generateToken(User user);
    String extractUsername(String token);
}
