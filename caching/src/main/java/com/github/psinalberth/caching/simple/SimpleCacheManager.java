package com.github.psinalberth.caching.simple;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SimpleCacheManager<K, V> {

    private final ConcurrentMap<K, CacheEntry<V>> data = new ConcurrentHashMap<>();

    public V get(K key) {
        CacheEntry<V> entry = data.get(key);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            data.remove(key);
            return null;
        }

        return entry.getValue();
    }

    public void put(K key, V value, Long ttl) {
        data.put(key, new CacheEntry<>(value, ttl));
    }
}
