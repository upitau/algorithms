package com.upit.algo.sort;

public class InsertionSort implements Sort {

    @Override
    public void sort(Comparable[] values) {
        for (int i = 1; i < values.length; i++) {
            int j = i;
            while (j > 0 && less(values[j], values[j-1])) {
                swap(values, j, j-1);
                j--;
            }
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
