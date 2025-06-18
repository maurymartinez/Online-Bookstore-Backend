package com.quasas.bookstore.security.application.service;

public interface PasswordEncoder {
    String encode(String rawPassword);
}
