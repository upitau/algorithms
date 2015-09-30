package com.upit.algo.other;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BinarySearchTest {
    private static int[] sortedHaystack = new int[] {6, 13, 14, 25, 33, 43, 51, 53, 64, 72, 84, 93, 95, 96, 97};

    private BinarySearch binarySearch = new BinarySearch();

    @Test
    public void shouldFindWhenNeedleExists() {
        assertThat(binarySearch.find(sortedHaystack, 33), is(4));

    }

    @Test
    public void shouldFindFirst() {
        assertThat(binarySearch.find(sortedHaystack, 6), is(0));

    }

    @Test
    public void shouldFindLast() {
        assertThat(binarySearch.find(sortedHaystack, 97), is(14));

    }

    @Test
    public void shouldNotFindWhenNeedleIsMissing() {
        assertThat(binarySearch.find(sortedHaystack, 34), is(-1));

    }

    @Test
    public void shouldNotFindWhenNeedleLessThanFirst() {
        assertThat(binarySearch.find(sortedHaystack, 3), is(-1));

    }

    @Test
    public void shouldNotFindWhenNeedleMoreThanLast() {
        assertThat(binarySearch.find(sortedHaystack, 100), is(-1));

    }
}