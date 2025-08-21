package com.github.psinalberth.basics.datastructure.data;

public class Node<T> {

    private final T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }
}
