package com.upit.algo.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LSDRadixSortTest {

    @Test
    public void shouldSortStably() {
        String[] input = {
                "dab",
                "add",
                "cab",
                "fad",
                "fee",
                "bad",
                "dad",
                "bee",
                "fed",
                "bed",
                "ebb",
                "ace"
        };

        String[] sorted = {
                "ace",
                "add",
                "bad",
                "bed",
                "bee",
                "cab",
                "dab",
                "dad",
                "ebb",
                "fad",
                "fed",
                "fee"
        };

        new LSDRadixSort().sort(input, 3);
        assertThat(input, is(sorted));
    }

}