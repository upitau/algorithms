package com.upit.algo.shuffle;

import java.util.Random;

public class KnuthShuffle implements Shuffle {
    @Override
    public void shuffle(Comparable[] values) {
        int n = values.length;

        Random random = new Random();
        for (int i = 1; i < n; i++) {
            int randomIndex = random.nextInt(i + 1);
            swap(values, i, randomIndex);
        }
    }

    private void swap(Comparable[] values, int i, int j) {
        Comparable temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}
