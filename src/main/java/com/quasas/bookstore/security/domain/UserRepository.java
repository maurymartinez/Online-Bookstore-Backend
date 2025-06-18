package com.quasas.bookstore.security.domain;

import com.quasas.bookstore.security.domain.valueobject.Email;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    boolean existsByEmail(Email email);
    Optional<User> findByEmail(Email email);
}
