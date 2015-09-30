package com.upit.algo.sort;

public class MergeSort implements Sort {

    @Override
    public void sort(Comparable[] values) {
        Comparable[] aux = new Comparable[values.length];
        sort(values, aux, 0, values.length - 1);
    }

    private void sort(Comparable[] values, Comparable[] aux, int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            sort(values, aux, first, middle);
            sort(values, aux, middle + 1, last);
            merge(values, aux, first, middle, last);
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
