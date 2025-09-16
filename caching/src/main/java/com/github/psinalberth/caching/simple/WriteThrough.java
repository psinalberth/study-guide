package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.CustomCache;
import com.github.psinalberth.caching.CustomDatabase;

import java.util.HashMap;
import java.util.Map;

public class WriteThrough<K, V> implements CustomCache<K, V> {

    private final CustomDatabase<K, V> database;
    private final Map<K, V> data = new HashMap<>();

    public WriteThrough(CustomDatabase<K, V> database) {
        this.database = database;
    }

    @Override
    public V get(K key) {
        return data.get(key);
    }

    @Override
    public void put(K key, V value) {
        data.put(key, value);
        database.store(key, value);
    }

    @Override
    public void evict(K key) {
        data.remove(key);
    }
}
