package com.upit.algo.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<T> implements Queue<T> {
    private static int INITIAL_CAPACITY = 10;
    private Object[] items = new Object[INITIAL_CAPACITY];
    private int head = 0;
    private int tail = 0;

    @Override
    public void enqueue(T item) {
        items[tail] = item;
        tail = (tail + 1) % items.length;
        ensureCapacity();
    }

    @Override
    public T dequeue() {
        checkNotEmpty();
        T item = (T) items[head];
        head = (head + 1) % items.length;
        shrinkCapacity();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    private void ensureCapacity() {
        if (head == tail) {
            resize(items.length * 2);
        }
    }

    private void shrinkCapacity() {
        if (size() == items.length / 4 && size() >= 2 * INITIAL_CAPACITY) {
            resize(items.length / 2);
        }
    }

    private void resize(int newSize) {
        Object[] oldItems = items;
        items = new Object[newSize];
        if (head < tail) {
            System.arraycopy(oldItems, head, items, 0, tail - head);
        } else {
            System.arraycopy(oldItems, head, items, 0, oldItems.length - head);
            System.arraycopy(oldItems, 0, items, oldItems.length - head, tail);
        }
        head = 0;
        tail = items.length / 2;
    }

    private int size() {
        return head <= tail ? tail - head : items.length - head + tail;
    }

    @Override
    public Iterator<T> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<T> {
        private int current = head;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            checkNotEmpty();
            T item = (T) items[current];
            current = (current + 1) % items.length;
            return item;
        }

        private void checkNotEmpty() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        }
    }
}
