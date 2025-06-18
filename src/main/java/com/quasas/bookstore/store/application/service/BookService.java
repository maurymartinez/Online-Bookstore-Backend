package com.quasas.bookstore.store.application.service;

import com.quasas.bookstore.store.domain.Book;
import com.quasas.bookstore.store.domain.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
