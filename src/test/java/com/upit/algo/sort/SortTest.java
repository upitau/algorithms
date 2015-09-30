package com.upit.algo.sort;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public abstract class SortTest {
    protected Sort sort;

    @Test
    public void shouldNotChangeWhenAlreadySorted() {
        Integer[] sorted = {1, 2, 3, 4, 5};
        sort.sort(sorted);
        Assert.assertThat(isSorted(sorted), is(true));
    }

    @Test
    public void shouldSortWhenReversed() {
        Integer[] reversed = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort.sort(reversed);
        Assert.assertThat(isSorted(reversed), is(true));
    }

    @Test
    public void shouldSortNonSortedWhenOdd() {
        Integer[] items = {-5, -40, 33, 12, 1};
        sort.sort(items);
        Assert.assertThat(isSorted(items), is(true));
    }

    @Test
    public void shouldSortNonSortedWhenEven() {
        Integer[] items = {-5, -40, 33, 12, 1, - 4};
        sort.sort(items);
        Assert.assertThat(isSorted(items), is(true));
    }

    @Test
    public void shouldSortNonSortedWithDuplicates() {
        Integer[] items = {-5, -40, 33, 12, 1, 33, 33, 1, -40, 1, -40};
        sort.sort(items);
        Assert.assertThat(isSorted(items), is(true));
    }

    private boolean isSorted(Integer[] values) {
        for (int i = 1; i < values.length; i++) {
            if (values[i].compareTo(values[i-1]) < 0) {
                return false;
            }
        }
        return true;
    }

}