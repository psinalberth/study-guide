package com.github.psinalberth.caching.simple;

public class CacheEntry<V> {

    private final V value;
    private final long expireInMillis;

    public CacheEntry(V value, long expireInMillis) {
        this.value = value;
        this.expireInMillis = System.currentTimeMillis() + expireInMillis;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.expireInMillis;
    }

    public V getValue() {
        return value;
    }
}
