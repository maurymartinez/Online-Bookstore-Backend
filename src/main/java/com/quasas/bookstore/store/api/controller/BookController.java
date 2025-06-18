package com.quasas.bookstore.store.api.controller;

import com.quasas.bookstore.store.api.dto.BookResponse;
import com.quasas.bookstore.store.application.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookResponse> getBooks() {
        return bookService.getBooks().stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }
}
