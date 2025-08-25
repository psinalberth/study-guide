package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.domain.Product;
import com.github.psinalberth.caching.domain.SimpleProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WriteThroughServiceTest {

    @Spy
    private SimpleCacheManager<String, Product> cacheManager = new SimpleCacheManager<>();

    @Spy
    private SimpleProductRepository productRepository = new SimpleProductRepository();

    @InjectMocks
    private WriteThroughService service;

    final String sku = "MCP15637";

    @Test
    @DisplayName("It should update product on database and cache")
    void shouldUpdateProductOnDatabaseAndCache() {

        cacheManager.put(sku, new Product(sku, "Some Product"), Duration.ofSeconds(1).toMillis());

        var product = new Product(sku, "My Product");

        service.updateProduct(product);

        var productFetched = service.findProduct(sku);

        assertThat(productFetched)
                .isNotNull()
                .satisfies(p -> {
                    assertThat(p.name()).isEqualTo("My Product");
                    assertThat(p.updatedAt()).isNotNull();
                });

        verify(cacheManager, times(2))
                .put(eq(sku), any(), any());

        verify(productRepository, times(1))
                .update(any());

        verify(productRepository, never())
                .findBySku(any());
    }
}