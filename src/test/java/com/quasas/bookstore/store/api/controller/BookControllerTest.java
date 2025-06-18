package com.quasas.bookstore.store.api.controller;

import com.quasas.bookstore.store.application.service.BookService;
import com.quasas.bookstore.store.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser
    void getAll_shouldReturn200_whenAuthenticated() throws Exception {
        when(bookService.getBooks()).thenReturn(List.of(
                new Book(UUID.randomUUID(), "Title", "Author", new BigDecimal("1.0"), 3)
        ));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk());
    }
}