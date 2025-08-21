package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.domain.Product;
import com.github.psinalberth.caching.domain.SimpleProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CacheAsideService {

    private static final Logger log = LoggerFactory.getLogger(CacheAsideService.class);

    private final SimpleCacheManager<String, Product> cacheManager;
    private final SimpleProductRepository productRepository;

    public CacheAsideService(
            SimpleCacheManager<String, Product> cacheManager,
            SimpleProductRepository productRepository
    ) {
        this.cacheManager = cacheManager;
        this.productRepository = productRepository;
    }

    public Product findProduct(String sku) {
        log.info("Fetching product with SKU '{}' from cache", sku);
        Product product = cacheManager.get(sku);

        if (product == null) {
            log.info("Product with SKU '{}' not in cache.", sku);
            product = productRepository.findBySku(sku);
            cacheManager.put(sku, product, Duration.ofSeconds(1).toMillis());
        }

        return product;
    }
}
