package com.github.psinalberth.caching.domain;

import java.time.LocalDateTime;

public record Product(String sku, String name, LocalDateTime updatedAt) {

    public Product(String sku, String name) {
        this(sku, name, null);
    }

    public Product update() {
        return new Product(sku(), name(), LocalDateTime.now());
    }
}
