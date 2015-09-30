package com.upit.algo.sort;

public class BottomUpMergeSort implements Sort {

    @Override
    public void sort(Comparable[] values) {
        int n = values.length;
        Comparable[] aux = new Comparable[n];

        for (int size = 1; size < n; size *= 2) {
            for (int first = 0; first < n - size; first += 2 * size) {
                int middle = first + size - 1;
                int last = Math.min(first + 2 * size - 1, n - 1);
                merge(values, aux, first, middle, last);
            }
        }
    }

    private void merge(Comparable[] values, Comparable[] aux, int first, int middle, int last) {
        for (int i = first; i <= last; i++) {
            aux[i] = values[i];
        }

        int i = first;
        int j = middle + 1;
        for (int k = first; k <= last; k++) {
            if (i > middle) {
                values[k] = aux[j++];
            } else if (j > last) {
                values[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                values[k] = aux[j++];
            } else {
                values[k] = aux[i++];
            }
        }
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
