package com.upit.algo.other;

import java.util.Arrays;

public class MaxPriorityQueue {
    private static int MIN_SIZE = 16;

    private Comparable[] items = new Comparable[MIN_SIZE];
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(Comparable item) {
        if (size == items.length) {
            items = Arrays.copyOf(items, size * 2);
        }

        items[size++] = item;
        swim(size - 1);
    }

    private void swim(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (!less(parent, index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public Comparable removeMax() {
        checkNotEmpty();

        Comparable maxItem = items[0];
        swap(0, --size);
        sink(0);

        if (size <= items.length / 4 && size > MIN_SIZE) {
            items = Arrays.copyOf(items, items.length / 2);
        }

        return maxItem;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    private void sink(int index) {
        while (firstChild(index) < size) {
            int child = firstChild(index);
            if (child + 1 < size && less(child, child + 1)) {
                child++;
            }

            if (less(index, child)) {
                swap(index, child);
                index = child;
            } else {
                break;
            }
        }
    }

    private int firstChild(int index) {
        return (index + 1) * 2 - 1;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void swap(int i, int j) {
        Comparable temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

}
