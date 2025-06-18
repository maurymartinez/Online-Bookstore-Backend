package com.quasas.bookstore.store.domain;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
}
