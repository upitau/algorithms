package com.upit.algo.sort;

public class SelectionSort implements Sort {

    @Override
    public void sort(Comparable[] values) {
        for (int i = 0; i < values.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < values.length; j++) {
                if (less(values[j], values[minIndex])) {
                    minIndex = j;
                }
            }
            swap(values, i, minIndex);
        }
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
