package com.github.psinalberth.basics.datastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class QueueTest {

    @Test
    @DisplayName("It should initialize queue")
    void shouldInitializeQueue() {
        Integer element = 10;
        Queue<Integer> queue = new Queue<>(element);

        assertThat(queue.size()).isEqualTo(1);
        assertThat(queue.contains(element)).isTrue();
    }

    @Test
    @DisplayName("It should enqueue element")
    void shouldEnqueueElement() {
        Integer element = 15;
        Queue<Integer> queue = new Queue<>(10);

        queue.enqueue(element);

        assertThat(queue.size()).isEqualTo(2);
        assertThat(queue.contains(element)).isTrue();
    }

    @Test
    @DisplayName("It should dequeue element")
    void shouldDequeueElement() {
        Integer element = 10;
        Queue<Integer> queue = new Queue<>(element);

        queue.enqueue(15);
        Integer removed = queue.dequeue();

        assertThat(queue.size()).isEqualTo(1);
        assertThat(queue.contains(element)).isFalse();
        assertThat(removed).isEqualTo(element);
    }

    @Test
    @DisplayName("It should dequeue element and empty queue")
    void shouldDequeueElementAndEmptyQueue() {
        Integer element = 10;
        Queue<Integer> queue = new Queue<>(element);

        Integer removed = queue.dequeue();

        assertThat(queue.size()).isEqualTo(0);
        assertThat(queue.contains(element)).isFalse();
        assertThat(removed).isEqualTo(element);
    }
}