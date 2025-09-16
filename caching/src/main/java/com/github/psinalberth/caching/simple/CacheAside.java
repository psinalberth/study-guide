package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.CacheStrategy;
import com.github.psinalberth.caching.CustomCache;
import com.github.psinalberth.caching.CustomDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class CacheAside<K, V> implements CacheStrategy<K, V> {

    private final CustomCache<K, V> cache;
    private final CustomDatabase<K, V> database;

    private static final Logger log = LoggerFactory.getLogger(CacheAside.class);

    public CacheAside(CustomCache<K, V> cache, CustomDatabase<K, V> database) {
        this.cache = cache;
        this.database = database;
    }

    public V get(K key) {
        log.info("Fetching entry with key: {}", key);
        V value = cache.get(key);

        if (Objects.nonNull(value)) {
            log.info("Cache hit matched for key: {}, value: {}", key, value);
            return value;
        }

        log.info("Cache miss for key: {}", key);
        value = database.get(key);

        if (Objects.nonNull(value)) {
            log.info("Updating cache entry for key: {}", key);
            cache.put(key, value);
        }

        return value;
    }

    public void put(K key, V value) {
        database.store(key, value);
        cache.evict(key);
    }
}
