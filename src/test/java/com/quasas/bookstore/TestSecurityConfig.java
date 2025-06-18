package com.quasas.bookstore;

import com.quasas.bookstore.security.infrastructure.SecurityConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(SecurityConfig.class)
public class TestSecurityConfig {
}
