package com.upit.algo.sort;

public class LSDRadixSort {
    private static int RADIX = 256;

    public void sort(String[] words, int wordLength) {
        String[] aux = new String[words.length];

        for (int charIndex = wordLength - 1; charIndex >= 0; charIndex--) {
            int[] offsets = new int[RADIX + 1];

            for (int i = 0; i < words.length; i++) {
                char ch = words[i].charAt(charIndex);
                offsets[ch+1]++;
            }

            for (int r = 0; r < RADIX; r++) {
                offsets[r+1] += offsets[r];
            }

            for (int i = 0; i < words.length; i++) {
                char ch = words[i].charAt(charIndex);
                aux[offsets[ch]++] = words[i];
            }

            for (int i = 0; i < words.length; i++) {
                words[i] = aux[i];
            }
        }
    }
}
