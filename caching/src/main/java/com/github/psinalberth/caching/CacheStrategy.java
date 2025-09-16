package com.github.psinalberth.caching;

public interface CacheStrategy<K, V> {

    V get(K key);

    void put(K key, V value);
}
