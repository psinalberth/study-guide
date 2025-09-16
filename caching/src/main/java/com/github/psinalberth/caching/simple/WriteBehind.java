package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.CustomCache;
import com.github.psinalberth.caching.CustomDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WriteBehind<K, V> implements CustomCache<K, V> {

    private final CustomDatabase<K, V> database;
    private final Map<K, V> data = new HashMap<>();
    private final Queue<Map.Entry<K, V>> syncDataQueue = new LinkedList<>();

    private static final Logger log = LoggerFactory.getLogger(WriteBehind.class);

    public WriteBehind(CustomDatabase<K, V> database) {
        this.database = database;
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::syncData, 0, 1, TimeUnit.MINUTES);
    }

    private void syncData() {
        Map.Entry<K, V> entry = syncDataQueue.poll();

        if (entry != null) {
            log.info("Syncing database with cached data: {}", entry);
            database.store(entry.getKey(), entry.getValue());
        }
    }

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
