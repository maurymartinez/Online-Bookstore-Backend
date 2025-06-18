package com.quasas.bookstore.security.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {
    @Test
    void shouldCreateEmailWhenFormatIsValid() {
        Email email = new Email("usuario@dominio.com");
        assertEquals("usuario@dominio.com", email.value());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    void shouldThrowExceptionWhenEmailFormatIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new Email("invalido-email"));
    }
}