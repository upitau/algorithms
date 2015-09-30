package com.upit.algo.queue;

public interface Queue<T> extends Iterable<T> {

    void enqueue(T item);

    T dequeue();

    boolean isEmpty();
}
