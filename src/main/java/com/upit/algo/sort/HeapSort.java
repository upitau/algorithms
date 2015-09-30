package com.upit.algo.sort;

public class HeapSort implements Sort {
    @Override
    public void sort(Comparable[] items) {
        int size = items.length;
        for (int i = size / 2 - 1; i >= 0; i--) {
            sink(items, size, i);
        }
        while (size > 0) {
            swap(items, 0, --size);
            sink(items, size, 0);
        }
    }

    private void sink(Comparable[] items, int size, int index) {
        while (index < size / 2) {
            int maxChild = firstChild(index);
            if (maxChild + 1 < size && less(items[maxChild], items[maxChild + 1])) {
                maxChild++;
            }
            if (less(items[index], items[maxChild])) {
                swap(items, index, maxChild);
                index = maxChild;
            } else {
                break;
            }
        }
    }

    private int firstChild(int parent) {
        return parent * 2 + 1;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void swap(Comparable[] items, int i, int j) {
        Comparable temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}
