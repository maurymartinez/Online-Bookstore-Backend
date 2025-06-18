package com.quasas.bookstore.security.infrastructure.repository;

import com.quasas.bookstore.security.domain.User;
import com.quasas.bookstore.security.domain.UserRepository;
import com.quasas.bookstore.security.domain.valueobject.Email;
import com.quasas.bookstore.security.domain.valueobject.PasswordHash;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final SpringDataUserRepository repository;

    @Override
    public void save(User user) {
        UserEntity entity = UserEntity.builder()
                .id(user.id())
                .email(user.email().value())
                .name(user.name())
                .password(user.password().value())
                .build();

        repository.save(entity);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return repository.existsByEmail(email.value());
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return repository.findByEmail(email.value())
                .map(entity -> new User(
                        entity.getId(),
                        new Email(entity.getEmail()),
                        entity.getName(),
                        new PasswordHash(entity.getPassword())
                ));
    }
}
