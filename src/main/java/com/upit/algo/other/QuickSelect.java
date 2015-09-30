package com.upit.algo.other;

import com.upit.algo.shuffle.KnuthShuffle;

public class QuickSelect {

    public Comparable select(Comparable[] items, int k) {
        new KnuthShuffle().shuffle(items);

        int lo = 0;
        int hi = items.length - 1;
        while (hi > lo) {
            int pivot = partition(items, lo, hi);
            if (pivot < k) {
                lo = pivot + 1;
            } else if (pivot > k) {
                hi = pivot - 1;
            } else {
                break;
            }
        }
        return items[k];
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
