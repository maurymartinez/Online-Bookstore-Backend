package com.quasas.bookstore.security.api.dto;

public record RegisterRequest(String email, String password, String name) {
}
