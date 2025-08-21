package com.github.psinalberth.caching;

import com.github.psinalberth.caching.simple.SimpleCacheManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.awaitility.Awaitility.await;

class SimpleCacheManagerTest {

    @Test
    @DisplayName("It should initiliaze cache")
    void shouldInitializeCache() {
        String key = "foo";
        Integer value = 10;
        SimpleCacheManager<String, Integer> cacheManager = new SimpleCacheManager<>();

        cacheManager.put(key, value, Duration.ofSeconds(5).toMillis());
        Integer retrieved = cacheManager.get(key);

        assertThat(retrieved).isNotNull();
        assertThat(retrieved).isEqualTo(value);
    }

    @Test
    @DisplayName("It should return null when key not exists")
    void shouldReturnNullWhenKeyNotExists() {
        String key = "foo";
        SimpleCacheManager<String, Integer> cacheManager = new SimpleCacheManager<>();

        assertThat(cacheManager.get(key)).isNull();
    }

    @Test
    @DisplayName("It should return null when entry is expired")
    void shouldReturnNullWhenEntryIsExpired() {
        String key = "foo";
        Integer value = 10;
        SimpleCacheManager<String, Integer> cacheManager = new SimpleCacheManager<>();

        cacheManager.put(key, value, Duration.ofNanos(1).toMillis());

        await().atMost(Duration.ofSeconds(1))
                .untilAsserted(() -> assertThat(cacheManager.get(key)).isNull());

    }
}