package com.github.psinalberth.caching;

import java.util.HashMap;
import java.util.Map;

public class SimpleDatabase<K, V> implements CustomDatabase<K, V> {

    private final Map<K, V> data = new HashMap<>();

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public void store(K key, V value) {
        data.put(key, value);
    }
}
