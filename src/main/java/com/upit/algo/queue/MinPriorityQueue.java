package com.upit.algo.queue;

import java.util.Arrays;

public class MinPriorityQueue<T extends Comparable<T>> {
    private static int MIN_SIZE = 16;

    private T[] items = (T[]) new Comparable[MIN_SIZE];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(T item) {
        if (size == items.length) {
            items = Arrays.copyOf(items, size * 2);
        }

        items[size++] = item;
        swim(size - 1);
    }

    private void swim(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (less(parent, index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public T removeMin() {
        checkNotEmpty();

        T minItem = items[0];
        swap(0, --size);
        sink(0);

        if (size <= items.length / 4 && size > MIN_SIZE) {
            items = Arrays.copyOf(items, items.length / 2);
        }

        return minItem;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    private void sink(int index) {
        while (firstChild(index) < size) {
            int child = firstChild(index);
            if (child + 1 < size && less(child + 1, child)) {
                child++;
            }

            if (less(child, index)) {
                swap(index, child);
                index = child;
            } else {
                break;
            }
        }
    }

    private int firstChild(int index) {
        return index * 2 + 1;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void swap(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

}
