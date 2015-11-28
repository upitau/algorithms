package com.upit.algo.sort;

public class ThreeWayRadixQuickSort {

    public void sort(String[] words) {
        sort(words, 0, words.length - 1, 0);
    }

    private void sort(String[] words, int lo, int hi, int charIndex) {
        if (hi <= lo) {
            return;
        }

        int pivot = charAt(words[lo], charIndex);
        int lt = lo;
        int gt = hi;

        int i = lo + 1;
        while (i <= gt) {
            int ch = charAt(words[i], charIndex);
            if (ch < pivot) {
                swap(words, lt++, i++);
            } else if (ch > pivot) {
                swap(words, i, gt--);
            } else {
                i++;
            }
        }

        if (pivot >= 0) {
            sort(words, lo, lt - 1, charIndex);
            sort(words, lt, gt, charIndex + 1);
        }
        sort(words, gt + 1, hi, charIndex);
    }

    private int charAt(String word, int index) {
        return index < word.length() ? word.charAt(index) : -1;
    }

    private void swap(String[] words, int a, int b) {
        String temp = words[a];
        words[a] = words[b];
        words[b] = temp;
    }
}
