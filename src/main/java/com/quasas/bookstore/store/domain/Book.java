package com.quasas.bookstore.store.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Book(UUID id, String title, String author, BigDecimal price, int copies) {
}
