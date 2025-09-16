package com.github.psinalberth.caching;

public interface CustomDatabase<K, V> {

    V get(K key);

    void store(K key, V value);
}
