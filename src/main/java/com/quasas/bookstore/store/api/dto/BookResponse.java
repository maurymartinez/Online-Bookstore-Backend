package com.quasas.bookstore.store.api.dto;

import com.quasas.bookstore.store.domain.Book;

import java.math.BigDecimal;
import java.util.UUID;

public record BookResponse(UUID id, String title, String author, BigDecimal price) {
    public static BookResponse from(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.author(),
                book.price()
        );
    }
}
