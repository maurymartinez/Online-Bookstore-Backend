package com.quasas.bookstore.security.application.service;

import com.quasas.bookstore.security.domain.User;
import com.quasas.bookstore.security.domain.UserRepository;
import com.quasas.bookstore.security.domain.valueobject.Email;
import com.quasas.bookstore.security.domain.valueobject.PasswordHash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authService = new AuthService(userRepository, passwordEncoder, jwtService);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        String email = "user@example.com";
        String name = "Peter";
        String password = "rawPassword";
        String hashedPassword = "$2a$10$abcdefghijklmnopqrstuvwxabcdefghijklmnopqrstuvwx123456";

        when(userRepository.existsByEmail(new Email(email))).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn(hashedPassword);

        authService.register(email, password, name);

        verify(userRepository).save(argThat(
                user ->
                        user.getEmail().value().equals(email) &&
                        user.getName().equals(name) &&
                        user.getPassword().value().equals(hashedPassword)
        ));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        String invalidEmail = "not-an-email";
        String password = "123456";
        String name = "Invalid";

        assertThrows(IllegalArgumentException.class, () ->
                authService.register(invalidEmail, password, name));

        verifyNoInteractions(userRepository);
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        String email = "duplicate@example.com";
        String password = "password123";
        String name = "Duplicate";

        when(userRepository.existsByEmail(new Email(email))).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () ->
                authService.register(email, password, name));

        verify(userRepository, never()).save(any());
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void login_shouldSucceed_whenCredentialsAreValid() {
        String password = "rawPassword";
        String hashedPassword = "$2a$10$abcdefghijklmnopqrstuvwxabcdefghijklmnopqrstuvwx123456";
        Email email = new Email("user@example.com");

        User user = new User(UUID.randomUUID(), email, "Test User", new PasswordHash(hashedPassword));

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, hashedPassword)).thenReturn(true);

        assertDoesNotThrow(() -> authService.login(email.value(), password));
    }
}