package com.github.psinalberth.caching.simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LRUCacheTest {

    LRUCache<Integer, String> cache = new LRUCache<>(3);

    @BeforeEach
    void beforeEach() {
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
    }

    @Test
    @DisplayName("It should initialize cache structure successfully")
    void shouldInitializeCache() {

        assertThat(cache)
                .isNotNull()
                .returns("A", lruCache -> lruCache.get(1))
                .returns("B", lruCache -> lruCache.get(2))
                .returns("C", lruCache -> lruCache.get(3));
    }

    @Test
    @DisplayName("It should put values to cache")
    void shouldGetCacheKeys() {

        cache.put(4, "D");
        cache.put(3, "F");

        assertThat(cache)
                .isNotNull()
                .returns("D", lruCache -> lruCache.get(4))
                .returns("F", lruCache -> lruCache.get(3))
                .returns("B", lruCache -> lruCache.get(2))
                .returns(null, lruCache -> lruCache.get(1));
    }
}