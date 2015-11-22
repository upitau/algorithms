package com.upit.algo.other;

import java.util.Arrays;

public class LongestRepeatedSubstring {

    public String find(String source) {
        int n = source.length();
        String[] suffixes = new String[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = source.substring(i);
        }

        Arrays.sort(suffixes);

        String result = "";

        for (int i = 0; i < n - 1; i++) {
            int d = 0;
            while (true) {
                int ch1 = charAt(suffixes[i], d);
                int ch2 = charAt(suffixes[i + 1], d);
                if (ch1 < 0 || ch2 < 0 || ch1 != ch2) {
                    break;
                }
                d++;
            }
            if (d > result.length()) {
                result = suffixes[i].substring(0, d);
            }
        }

        return result;
    }


    private int charAt(String word, int index) {
        return index < word.length() ? word.charAt(index) : -1;
    }
}
