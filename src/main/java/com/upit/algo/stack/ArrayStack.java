package com.upit.algo.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {
    private static int DEFAULT_CAPACITY = 10;

    private Object[] items = new Object[DEFAULT_CAPACITY];
    private int n = 0;

    @Override
    public void push(T item) {
        ensureCapacity();
        items[n++] = item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T pop() {
        checkNotEmpty();
        T item = (T) items[--n];
        items[n] = null;
        shrinkCapacity();
        return item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
    }

    private void ensureCapacity() {
        if (n == items.length) {
            Object[] oldItems = items;
            items = new Object[n * 2];
            System.arraycopy(oldItems, 0, items, 0, n);
        }
    }

    private void shrinkCapacity() {
        if (items.length >= 2 * DEFAULT_CAPACITY && n == items.length / 4) {
            Object[] oldItems = items;
            items = new Object[items.length / 2];
            System.arraycopy(oldItems, 0, items, 0, n);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ItemIterator();
    }

    private class ItemIterator implements Iterator<T> {
        private int current = n;

        @Override
        public boolean hasNext() {
            return current > 0;
        }

        @Override
        public T next() {
            checkNotEmpty();
            return (T) items[--current];
        }

        private void checkNotEmpty() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
        }
    }
}
