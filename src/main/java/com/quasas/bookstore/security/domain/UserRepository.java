package com.quasas.bookstore.security.domain;

import com.quasas.bookstore.security.domain.valueobject.Email;

public interface UserRepository {
    void save(User user);
    boolean existsByEmail(Email email);
}
