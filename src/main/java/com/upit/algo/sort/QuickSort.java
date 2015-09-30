package com.upit.algo.sort;

import com.upit.algo.shuffle.KnuthShuffle;

public class QuickSort implements Sort {

    @Override
    public void sort(Comparable[] items) {
        new KnuthShuffle().shuffle(items);
        sort(items, 0, items.length - 1);
    }

    private void sort(Comparable[] items, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        int pivot = partition(items, lo, hi);
        sort(items, lo, pivot - 1);
        sort(items, pivot + 1, hi);
    }

    private int partition(Comparable[] items, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(items[++i], items[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while (less(items[lo], items[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swap(items, i, j);
        }
        swap(items, lo, j);
        return j;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void swap(Comparable[] values, int i, int j) {
        Comparable temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}
