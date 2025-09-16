package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.CustomCache;
import com.github.psinalberth.caching.CustomDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReadThrough<K, V> implements CustomCache<K, V> {

    private final Map<K, V> data = new HashMap<>();
    private final CustomDatabase<K, V> database;

    private static final Logger log = LoggerFactory.getLogger(ReadThrough.class);

    public ReadThrough(CustomDatabase<K, V> database) {
        this.database = database;
    }

    @Override
    public V get(K key) {

        log.info("Fetching entry with key: {}", key);
        V value = data.get(key);

        if (Objects.nonNull(value)) {
            log.info("Cache hit matched for key: {}, value: {}", key, value);
            return value;
        }

        log.info("Updating cache entry for key: {}", key);
        data.put(key, database.get(key));

        return data.get(key);
    }

    @Override
    public void put(K key, V value) {
        data.put(key, value);
    }

    @Override
    public void evict(K key) {
        data.remove(key);
    }
}
