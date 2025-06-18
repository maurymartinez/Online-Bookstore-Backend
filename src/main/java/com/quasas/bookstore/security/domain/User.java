package com.quasas.bookstore.security.domain;

import com.quasas.bookstore.security.domain.valueobject.Email;
import com.quasas.bookstore.security.domain.valueobject.PasswordHash;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {

    private final UUID id;
    private final Email email;
    private final String name;
    private final PasswordHash password;

    public boolean verifyPassword(String rawPassword, PasswordVerifier verifier) {
        return verifier.verify(rawPassword, password.value());
    }

    public interface PasswordVerifier {
        boolean verify(String rawPassword, String hashed);
    }
}
