package com.github.psinalberth.caching;

import com.github.psinalberth.caching.domain.Product;
import com.github.psinalberth.caching.domain.SimpleProductRepository;
import com.github.psinalberth.caching.simple.CacheAsideService;
import com.github.psinalberth.caching.simple.SimpleCacheManager;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CacheAsideServiceTest {

    @Spy
    private SimpleCacheManager<String, Product> cacheManager = new SimpleCacheManager<>();

    @Spy
    private SimpleProductRepository productRepository = new SimpleProductRepository();

    @InjectMocks
    private CacheAsideService service;

    final String sku = "MCP15637";

    @Test
    @DisplayName("It should fetch product from database")
    void shouldFetchProductFromDatabase() {

        Product product = service.findProduct(sku);

        assertThat(product).isNotNull();

        verify(cacheManager, times(1))
                .get(sku);

        verify(productRepository, times(1))
                .findBySku(sku);

    }

    @Test
    @DisplayName("It should fetch product from cache")
    void shouldFetchProductFromCache() {

        String productDescription = "My Product";

        cacheManager.put(sku, new Product(sku, productDescription), Duration.ofSeconds(1).toMillis());
        Product product = service.findProduct(sku);

        assertThat(product).isNotNull()
                .satisfies(p -> assertThat(p.name()).isEqualTo(productDescription));

        verify(cacheManager, times(1))
                .get(sku);

        verify(productRepository, never())
                .findBySku(sku);
    }

    @Test
    @DisplayName("It should fetch product from database when cache is expired")
    void shouldFetchProductFromDatabaseWhenCacheIsExpired() {

        String productDescription = "My Product";

        cacheManager.put(sku, new Product(sku, productDescription), Duration.ofMillis(10).toMillis());

        Awaitility.await().atMost(Duration.ofSeconds(2))
                .untilAsserted(() -> {

                    Product product = service.findProduct(sku);

                    assertThat(product).isNotNull();

                    verify(cacheManager, times(1))
                            .get(sku);

                    verify(productRepository, times(1))
                            .findBySku(sku);
                });


    }
}