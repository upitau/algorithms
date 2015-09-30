package com.upit.algo.sort;

import com.upit.algo.shuffle.KnuthShuffle;

public class ThreeWayQuickSort implements Sort {

    @Override
    public void sort(Comparable[] items) {
        new KnuthShuffle().shuffle(items);
        sort(items, 0, items.length - 1);
    }

    private void sort(Comparable[] items, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        Comparable value = items[lo];
        int lt = lo;
        int gt = hi;
        int i = lo;

        while (i <= gt) {
            int cmp = items[i].compareTo(value);
            if (cmp < 0) {
                swap(items, lt++, i++);
            } else if (cmp > 0) {
                swap(items, i, gt--);
            } else {
                i++;
            }
        }
        sort(items, lo, lt - 1);
        sort(items, gt + 1, hi);
    }

    private void swap(Comparable[] items, int i, int j) {
        Comparable temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}
