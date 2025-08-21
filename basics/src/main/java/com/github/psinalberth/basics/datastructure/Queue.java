package com.github.psinalberth.basics.datastructure;

import com.github.psinalberth.basics.datastructure.data.Node;

public class Queue<T> {

    private Node<T> head;
    private Node<T> tail;

    public Queue(final T value) {
        Node<T> newNode = new Node<>(value);
        this.head = newNode;
        this.tail = newNode;
    }

    public void enqueue(final T value) {
        Node<T> newNode = new Node<>(value);
        this.tail.setNext(newNode);
        this.tail = newNode;
    }

    public T dequeue() {
        Node<T> head = this.head;

        if (head.equals(this.tail)) {
            this.tail = null;
        }

        this.head = head.getNext();
        return head.getValue();
    }

    public int size() {
        Node<T> head = this.head;
        int count = 0;

        while (head != null) {
            count++;
            head = head.getNext();
        }

        return count;
    }

    public boolean contains(T element) {
        Node<T> head = this.head;

        while (head != null) {
            if (head.getValue().equals(element))
                return true;
            head = head.getNext();
        }

        return false;
    }
}
