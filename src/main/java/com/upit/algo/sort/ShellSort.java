package com.upit.algo.sort;

public class ShellSort implements Sort {

    @Override
    public void sort(Comparable[] values) {
        int n = values.length;

        int h = 1;
        while (h < n / 3) {
            h = 3*h + 1;
        }

        while (h > 0) {
            for (int i = h; i < n; i++) {
                int j = i;
                while (j >= h && less(values[j], values[j-h])) {
                    swap(values, j, j-h);
                    j -= h;
                }
            }
            h /= 3;
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
