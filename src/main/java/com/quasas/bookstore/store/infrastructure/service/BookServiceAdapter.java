package com.quasas.bookstore.store.infrastructure.service;

import com.quasas.bookstore.store.application.service.BookService;
import com.quasas.bookstore.store.domain.BookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceAdapter {

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new BookService(bookRepository);
    }
}
