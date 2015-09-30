package com.upit.algo.shuffle;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ShuffleTest {
    protected Shuffle shuffle = new KnuthShuffle();

    @Test
    public void shouldShuffleSortedNumbers() {
        Integer[] sorted = new Integer[20];
        IntStream.range(0, sorted.length).forEach(i -> sorted[i] = i);
        shuffle.shuffle(sorted);
        System.out.println(Arrays.toString(sorted));
    }

}