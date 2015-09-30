package com.upit.algo.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {
    private Node<T> first;
    private Node<T> last;

    @Override
    public void enqueue(T item) {
        if (isEmpty()) {
            first = last = new Node<>(item);
        } else {
            last.next = new Node<>(item);
            last = last.next;
        }
    }

    @Override
    public T dequeue() {
        checkNotEmpty();
        T item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ItemIterator();
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }

    private class ItemIterator implements Iterator<T> {
        private Node<T> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            checkNotEmpty();
            T item = current.item;
            current = current.next;
            return item;
        }

        private void checkNotEmpty() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        }
    }
}
