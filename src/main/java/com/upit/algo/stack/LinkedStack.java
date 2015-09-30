package com.upit.algo.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T> {
    private Node<T> first;

    @Override
    public void push(T item) {
        Node<T> oldFirst = first;

        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
    }

    @Override
    public T pop() {
        checkNotEmpty();

        T item = first.item;
        first = first.next;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ItemIterator();
    }

    private static class Node<T> {
        T item;
        Node<T> next;
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
