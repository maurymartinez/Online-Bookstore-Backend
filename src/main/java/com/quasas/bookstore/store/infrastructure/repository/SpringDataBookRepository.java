package com.quasas.bookstore.store.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBookRepository extends JpaRepository<BookEntity, UUID> {
}
