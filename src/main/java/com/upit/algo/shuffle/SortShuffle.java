package com.upit.algo.shuffle;

import com.upit.algo.sort.MergeSort;
import com.upit.algo.sort.Sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SortShuffle implements Shuffle {

    @Override
    public void shuffle(Comparable[] values) {
        int n = values.length;

        Double[] randomNumbers = new Double[n];
        Map<Double, Integer> indexByRandomValue = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            Double randomNumber;
            do {
                randomNumber = random.nextDouble();
            } while (indexByRandomValue.containsKey(randomNumber));

            randomNumbers[i] = randomNumber;
            indexByRandomValue.put(randomNumber, i);
        }

        Sort sort = new MergeSort();
        sort.sort(randomNumbers);

        for (int i = 0; i < n; i++) {
            int destIndex = indexByRandomValue.get(randomNumbers[i]);
            if (destIndex > i) {
                swap(values, destIndex, i);
            }
        }
    }

    private void swap(Comparable[] values, int i, int j) {
        Comparable temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }
}
