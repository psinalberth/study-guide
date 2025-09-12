package com.github.psinalberth.caching.simple;

import java.util.*;

public class LRUCache<K, V> {

    private final int capacity;
    private final Map<K, V> cache;
    private final LinkedList<K> keyList;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.keyList = new LinkedList<>();
    }

    public void put(K key, V value) {

        if (keyList.contains(key)) {
            keyList.remove(key);
            cache.remove(key);
        }

        cache.put(key, value);
        keyList.addFirst(key);

        if (cache.size() > capacity) {
            K lastElementKey = keyList.removeLast();
            cache.remove(lastElementKey);
        }
    }

    public V get(K key) {

        if (!keyList.contains(key)) {
            return null;
        }

        keyList.remove(key);
        keyList.addFirst(key);

        return cache.get(key);
    }

    @Override
    public String toString() {
        return "LRUCache{" +
                "capacity=" + capacity +
                ", cache=" + cache +
                ", keyList=" + keyList +
                '}';
    }
}
