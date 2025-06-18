package com.quasas.bookstore.security.domain;

import com.quasas.bookstore.security.domain.valueobject.Email;
import com.quasas.bookstore.security.domain.valueobject.PasswordHash;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    void verifyPassword_returnsTrue_whenPasswordMatches() {
        var user = new User(
                UUID.randomUUID(),
                new Email("test@example.com"),
                "Test User",
                new PasswordHash("$2a$10$abcdefg1234567890abcdefg1234567890abcdefg1234567890abcdefg")
        );

        User.PasswordVerifier mockVerifier = (raw, hash) -> true;

        assertTrue(user.verifyPassword("any-password", mockVerifier));
    }

    @Test
    void verifyPassword_returnsFalse_whenPasswordDoesNotMatch() {
        var user = new User(
                UUID.randomUUID(),
                new Email("test@example.com"),
                "Test User",
                new PasswordHash("$2a$10$abcdefg1234567890abcdefg1234567890abcdefg1234567890abcdefg")
        );

        User.PasswordVerifier mockVerifier = (raw, hash) -> false;

        assertFalse(user.verifyPassword("wrong-password", mockVerifier));
    }
}