package com.upit.algo.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MSDRadixSortTest {

    @Test
    public void shouldSortWordsWithDifferentLengths() {
        String[] input = {
                "she",
                "sells",
                "seashells",
                "by",
                "the",
                "sea",
                "shore",
                "the",
                "shells",
                "she",
                "sells",
                "are",
                "surely",
                "seashells"
        };

        String[] expected = {
                "are",
                "by",
                "sea",
                "seashells",
                "seashells",
                "sells",
                "sells",
                "she",
                "she",
                "shells",
                "shore",
                "surely",
                "the",
                "the"
        };

        new MSDRadixSort().sort(input);
        assertThat(input, is(expected));
    }

}