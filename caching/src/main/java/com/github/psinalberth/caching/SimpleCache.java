package com.github.psinalberth.caching;

import java.util.HashMap;
import java.util.Map;

public class SimpleCache<K, V> implements CustomCache<K, V> {

    private final Map<K, V> data = new HashMap<>();

    @Override
    public V get(K key) {
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
