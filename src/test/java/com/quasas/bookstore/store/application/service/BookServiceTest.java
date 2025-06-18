package com.quasas.bookstore.store.application.service;

import com.quasas.bookstore.store.domain.Book;
import com.quasas.bookstore.store.domain.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void getBooks_returnsListOfBooks() {
        List<Book> expectedBooks = List.of(
                new Book(UUID.randomUUID(), "Title 1", "Author 1", new BigDecimal("10.99"), 4),
                new Book(UUID.randomUUID(), "Title 2", "Author 2", new BigDecimal("15.50"), 5)
        );

        when(bookRepository.findAll()).thenReturn(expectedBooks);

        List<Book> actualBooks = bookService.getBooks();

        assertEquals(expectedBooks.size(), actualBooks.size());
        assertEquals(expectedBooks.get(0).title(), actualBooks.get(0).title());
        assertEquals(expectedBooks.get(1).author(), actualBooks.get(1).author());

        verify(bookRepository, times(1)).findAll();
    }
}