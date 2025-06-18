package com.quasas.bookstore.security.domain.valueobject;

public record PasswordHash(String value) {
    public PasswordHash {
        if (value == null || value.length() < 60) {
            throw new IllegalArgumentException("Invalid password hash");
        }
    }
}
