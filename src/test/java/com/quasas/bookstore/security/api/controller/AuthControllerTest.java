package com.quasas.bookstore.security.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quasas.bookstore.TestSecurityConfig;
import com.quasas.bookstore.security.api.dto.RegisterRequest;
import com.quasas.bookstore.security.application.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}