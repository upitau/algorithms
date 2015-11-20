package com.upit.algo.sort;

public class MSDRadixSort {
    private static int RADIX = 256;

    public void sort(String[] words) {
        String[] aux = new String[words.length];
        sort(words, aux, 0, words.length - 1, 0);
    }

    private void sort(String[] words, String[] aux, int lo, int hi, int charIndex) {
        if (hi <= lo) {
            return;
        }

        int[] offsets = new int[RADIX + 2];

        for (int i = lo; i <= hi; i++) {
            int ch = charAt(words[i], charIndex);
            offsets[ch+2]++;
        }

        for (int r = 0; r < RADIX + 1; r++) {
            offsets[r+1] += offsets[r];
        }

        for (int i = lo; i <= hi; i++) {
            int ch = charAt(words[i], charIndex);
            aux[lo + offsets[ch+1]++] = words[i];
        }

        for (int i = lo; i <= hi; i++) {
            words[i] = aux[i];
        }

        for (int r = 0; r < RADIX; r++) {
            sort(words, aux, lo + offsets[r], lo + offsets[r+1] - 1, charIndex + 1);
        }
    }

    private int charAt(String word, int index) {
        return index < word.length() ? word.charAt(index) : -1;
    }
}
