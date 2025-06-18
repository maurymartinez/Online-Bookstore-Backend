package com.quasas.bookstore.security.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasas.bookstore.TestSecurityConfig;
import com.quasas.bookstore.security.api.dto.LoginRequest;
import com.quasas.bookstore.security.api.dto.RegisterRequest;
import com.quasas.bookstore.security.application.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterUserSuccessfully() throws Exception {
        RegisterRequest request = new RegisterRequest("test@example.com", "securePass", "Test User");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(authService).register("test@example.com", "securePass", "Test User");
    }

    @Test
    void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception {
        RegisterRequest request = new RegisterRequest("invalid-email", "pass", "Test User");

        doThrow(new IllegalArgumentException("invalid-email")).when(authService)
                .register(eq("invalid-email"), anyString(), anyString());

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenEmailIsInvalid() throws Exception {
        RegisterRequest request = new RegisterRequest("bad-email", "securePass", "User");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Invalid email format")));
    }

    @Test
    void shouldReturn400WhenPasswordTooShort() throws Exception {
        RegisterRequest request = new RegisterRequest("user@example.com", "123", "User");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Password must be at least 6 characters")));
    }

    @Test
    void shouldReturn400WhenFieldsAreBlank() throws Exception {
        RegisterRequest request = new RegisterRequest("", "", "");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Email is required")))
                .andExpect(content().string(containsString("Password must be at least 6 characters")))
                .andExpect(content().string(containsString("Name is required")));
    }

    @Test
    void login_returnsToken_whenCredentialsAreValid() throws Exception {
        LoginRequest request = new LoginRequest("user@example.com", "password123");
        String expectedToken = "mocked.jwt.token";

        when(authService.login(request.email(), request.password()))
                .thenReturn(expectedToken);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").value(expectedToken));
    }

    @Test
    void login_returnsUnauthorized_whenCredentialsAreInvalid() throws Exception {
        LoginRequest request = new LoginRequest("user@example.com", "wrongpass");

        when(authService.login(request.email(), request.password()))
                .thenThrow(new IllegalArgumentException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
