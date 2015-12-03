package com.upit.algo.substring;

public class KnuthMorrisPrattSubstringSearch {
    private static final int RADIX = 256;

    private int[][] dfa;

    public KnuthMorrisPrattSubstringSearch(String pattern) {
        int length = pattern.length();
        dfa = new int[RADIX][length];
        dfa[pattern.charAt(0)][0] = 1;
        for (int simulatedState = 0, i = 1; i < length; i++) {
            for (int j = 0; j < RADIX; j++) {
                dfa[j][i] = dfa[j][simulatedState];
            }
            char ch = pattern.charAt(i);
            dfa[ch][i] = i + 1;
            simulatedState = dfa[ch][simulatedState];
        }
    }

    public int indexOf(String text) {
        int finalState = dfa[0].length;
        for (int i = 0, j = 0; i < text.length(); i++) {
            j = dfa[text.charAt(i)][j];
            if (j == finalState) {
                return i + 1 - finalState;
            }
        }
        return -1;
    }
}
