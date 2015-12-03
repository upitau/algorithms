package com.upit.algo.substring;

import java.util.Arrays;

public class BoyerMooreSubstringSearch {
    private static final int RADIX = 256;

    private String pattern;
    private int[] right;

    public BoyerMooreSubstringSearch(String pattern) {
        this.pattern = pattern;
        right = new int[RADIX];
        Arrays.fill(right, -1);
        for (int i = 0; i < pattern.length(); i++) {
            right[pattern.charAt(i)] = i;
        }
    }

    public int indexOf(String text) {
        int n = text.length();
        int m = pattern.length();
        int skip = 0;
        for (int i = 0; i <= n - m; i += skip) {
            for (int j = m - 1; j >= 0; j--) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
                if (j == 0) {
                    return i;
                }
            }
        }
        return -1;
    }
}
