package com.quasas.bookstore.security.application.service;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String hashed);
}
