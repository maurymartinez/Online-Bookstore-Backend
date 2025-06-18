package com.quasas.bookstore.security.domain.valueobject;

public record Email(String value) {
    public Email {
        if (value == null || !value.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
