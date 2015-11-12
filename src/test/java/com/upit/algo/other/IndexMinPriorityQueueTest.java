package com.upit.algo.other;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class IndexMinPriorityQueueTest {
    private IndexMinPriorityQueue<Integer> indexMinPriorityQueue = new IndexMinPriorityQueue<>(10);

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(indexMinPriorityQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldBeEmptyWhenAllAddedAreRemoved() {
        indexMinPriorityQueue.insert(0, 1);
        indexMinPriorityQueue.removeMin();
        assertThat(indexMinPriorityQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldBeNotEmptyWhenHasItems() {
        indexMinPriorityQueue.insert(0, 1);
        assertThat(indexMinPriorityQueue.isEmpty(), is(false));
    }

    @Test
    public void shouldRemoveMinWhenInsertsAreOrdered() {
        indexMinPriorityQueue.insert(0, 1);
        indexMinPriorityQueue.insert(1, 2);
        indexMinPriorityQueue.insert(2, 3);
        assertThat(indexMinPriorityQueue.removeMin(), is(0));
    }

    @Test
    public void shouldRemoveMinWhenInsertsAreReversed() {
        indexMinPriorityQueue.insert(2, 3);
        indexMinPriorityQueue.insert(1, 2);
        indexMinPriorityQueue.insert(0, 1);
        assertThat(indexMinPriorityQueue.removeMin(), is(0));
    }

    @Test
    public void shouldRemoveSecondMinWhenInsertsAreNotOrdered() {
        indexMinPriorityQueue.insert(2, 3);
        indexMinPriorityQueue.insert(4, 20);
        indexMinPriorityQueue.insert(1, 1);
        indexMinPriorityQueue.insert(3, 12);
        indexMinPriorityQueue.insert(0, -6);
        indexMinPriorityQueue.removeMin();
        assertThat(indexMinPriorityQueue.removeMin(), is(1));
    }

    @Test
    public void shouldGetMinIndexWhenInsertsAreNotOrdered() {
        indexMinPriorityQueue.insert(2, 3);
        indexMinPriorityQueue.insert(4, -20);
        indexMinPriorityQueue.insert(1, 1);
        assertThat(indexMinPriorityQueue.minIndex(), is(4));
    }

    @Test
    public void shouldGetMinKeyWhenInsertsAreNotOrdered() {
        indexMinPriorityQueue.insert(2, 3);
        indexMinPriorityQueue.insert(4, -20);
        indexMinPriorityQueue.insert(1, 1);
        assertThat(indexMinPriorityQueue.minKey(), is(-20));
    }

    @Test
    public void shouldDeleteNewMinWhenNonMinKeyDecreased() {
        indexMinPriorityQueue.insert(2, 3);
        indexMinPriorityQueue.insert(4, 20);
        indexMinPriorityQueue.insert(1, 1);
        indexMinPriorityQueue.insert(3, 12);
        indexMinPriorityQueue.insert(0, -6);
        indexMinPriorityQueue.decreaseKey(3, -50);
        assertThat(indexMinPriorityQueue.removeMin(), is(3));
    }

    @Test
    public void shouldGetKeyOfIndex() {
        indexMinPriorityQueue.insert(2, 3);
        assertThat(indexMinPriorityQueue.keyOf(2), is(3));
    }

    @Test
    public void shouldGetUpdatedKeyOfIndexAfterDecrease() {
        indexMinPriorityQueue.insert(2, 3);
        indexMinPriorityQueue.decreaseKey(2, -50);
        assertThat(indexMinPriorityQueue.keyOf(2), is(-50));
    }

}