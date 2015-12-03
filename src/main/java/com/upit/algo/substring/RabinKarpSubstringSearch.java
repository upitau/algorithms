package com.upit.algo.substring;

public class RabinKarpSubstringSearch {
    private static final int RADIX = 256;
    private static final long PRIME = 997;

    private String pattern;
    private long patternHash;
    private long rm;

    public RabinKarpSubstringSearch(String pattern) {
        this.pattern = pattern;
        patternHash = hash(pattern, pattern.length());
        rm = 1;
        for (int i = 1; i < pattern.length(); i++) {
            rm = (rm * RADIX) % PRIME;
        }
    }

    public int indexOf(String text) {
        int n = text.length();
        int m = pattern.length();
        if (n < m) {
            return -1;
        }

        long h = hash(text, m);
        if (h == patternHash  && pattern.equals(text.substring(m))) {
            return 0;
        }

        for (int i = m; i < n; i++) {
            h = (h + PRIME - rm * text.charAt(i - m) % PRIME) % PRIME;
            h = (h * RADIX + text.charAt(i)) % PRIME;
            if (h == patternHash && pattern.equals(text.substring(i - m + 1, i + 1))) {
                return i - m + 1;
            }
        }

        return -1;
    }

    private long hash(String key, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash = (hash * RADIX + key.charAt(i)) % PRIME;
        }
        return hash;
    }
}