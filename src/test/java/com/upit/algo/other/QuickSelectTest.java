package com.upit.algo.other;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class QuickSelectTest {
    protected QuickSelect select = new QuickSelect();

    @Test
    public void shouldFindInSortedArray() {
        Integer[] sorted = {1, 2, 3, 4, 5};
        assertThat(select.select(sorted, 3), is(4));
    }

    @Test
    public void shouldFindFirst() {
        Integer[] items = {-5, -40, 33, 12, 1};
        assertThat(select.select(items, 0), is(-40));
    }

    @Test
    public void shouldFindLast() {
        Integer[] items = {-5, -40, 33, 12, 1};
        assertThat(select.select(items, 4), is(33));
    }

    @Test
    public void shouldFindMiddle() {
        Integer[] items = {-5, -40, 33, 12, 1};
        assertThat(select.select(items, 2), is(1));
    }

}