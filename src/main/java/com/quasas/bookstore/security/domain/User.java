package com.quasas.bookstore.security.domain;

import com.quasas.bookstore.security.domain.valueobject.Email;
import com.quasas.bookstore.security.domain.valueobject.PasswordHash;

import java.util.UUID;

public record User(UUID id, Email email, String name, PasswordHash password) {

    public boolean verifyPassword(String rawPassword, PasswordVerifier verifier) {
        return verifier.verify(rawPassword, password.value());
    }

    public interface PasswordVerifier {
        boolean verify(String rawPassword, String hashed);
    }
}
