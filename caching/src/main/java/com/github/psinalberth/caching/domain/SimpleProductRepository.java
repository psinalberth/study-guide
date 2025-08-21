package com.github.psinalberth.caching.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleProductRepository {

    private static final Logger log = LoggerFactory.getLogger(SimpleProductRepository.class);

    public Product findBySku(String sku) {
        log.info("Fetching product with SKU '{}' from database", sku);
        return new Product(sku, "Some Product");
    }

    public Product update(final Product product) {
        log.info("Updating product {}", product);
        return product.update();
    }
}
