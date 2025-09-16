package com.github.psinalberth.caching.simple;

import com.github.psinalberth.caching.CustomDatabase;
import com.github.psinalberth.caching.SimpleDatabase;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class WriteBehindTest {

    @Spy
    private CustomDatabase<Integer, String> database = new SimpleDatabase<>();

    @InjectMocks
    private WriteBehind<Integer, String> manager;

    @Test
    @DisplayName("It should init cache successfully")
    void shouldInit() {
        manager.put(1, "A");

        Awaitility.await()
                .untilAsserted(() -> database.store(any(), any()));
    }
}