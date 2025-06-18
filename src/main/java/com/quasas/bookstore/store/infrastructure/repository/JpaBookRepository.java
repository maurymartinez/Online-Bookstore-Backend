package com.quasas.bookstore.store.infrastructure.repository;

import com.quasas.bookstore.store.domain.Book;
import com.quasas.bookstore.store.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {

    private final SpringDataBookRepository repository;

    @Override
    public List<Book> findAll() {
        return repository.findAll().stream().map(entity -> new Book(
                entity.getId(),
                entity.getTitle(),
                entity.getAuthor(),
                entity.getPrice(),
                entity.getCopies()
        )).collect(Collectors.toList());
    }
}
