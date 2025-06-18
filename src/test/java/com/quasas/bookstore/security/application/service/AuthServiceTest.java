package com.quasas.bookstore.security.application.service;

import com.quasas.bookstore.security.domain.UserRepository;
import com.quasas.bookstore.security.domain.valueobject.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthService(userRepository, passwordEncoder);
    }

    @Test
    void shouldRegisterUserSuccessfully() {
        var email = "user@example.com";
        var name = "Peter";
        var password = "rawPassword";
        var hashedPassword = "$2a$10$abcdefghijklmnopqrstuvwxabcdefghijklmnopqrstuvwx123456";

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

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                authService.register(invalidEmail, password, name));

        verifyNoInteractions(userRepository);
    }
}