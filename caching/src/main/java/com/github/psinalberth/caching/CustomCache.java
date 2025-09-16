package com.github.psinalberth.caching;

public interface CustomCache<K, V> {

    V get(K key);

    void put(K key, V value);

    void evict(K key);
}
