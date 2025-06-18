package com.quasas.bookstore.security.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    @Test
    void shouldCreatePasswordHashWhenLengthIsValid() {
        String hash = "$2a$10$abcdefghijklmnopqrstuvwxabcdefghijklmnopqrstuvwx123456";
        PasswordHash passwordHash = new PasswordHash(hash);
        assertEquals(hash, passwordHash.value());
    }

    @Test
    void shouldThrowExceptionWhenPasswordHashIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new PasswordHash(null));
    }

    @Test
    void shouldThrowExceptionWhenPasswordHashIsTooShort() {
        assertThrows(IllegalArgumentException.class, () -> new PasswordHash("short"));
    }
}