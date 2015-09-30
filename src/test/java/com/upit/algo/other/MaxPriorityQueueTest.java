package com.upit.algo.other;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MaxPriorityQueueTest {
    private MaxPriorityQueue maxPriorityQueue = new MaxPriorityQueue();

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(maxPriorityQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldBeEmptyWhenAllAddedAreRemoved() {
        maxPriorityQueue.insert(15);
        maxPriorityQueue.removeMax();
        assertThat(maxPriorityQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldBeNotEmptyWhenHasItems() {
        maxPriorityQueue.insert(15);
        assertThat(maxPriorityQueue.isEmpty(), is(false));
    }

    @Test
    public void shouldGetMaxWhenInsertsAreOrdered() {
        maxPriorityQueue.insert(1);
        maxPriorityQueue.insert(2);
        maxPriorityQueue.insert(3);
        assertThat(maxPriorityQueue.removeMax(), is(3));
    }

    @Test
    public void shouldGetMaxWhenInsertsAreReversed() {
        maxPriorityQueue.insert(3);
        maxPriorityQueue.insert(2);
        maxPriorityQueue.insert(1);
        assertThat(maxPriorityQueue.removeMax(), is(3));
    }

    @Test
    public void shouldGetSecondMaxWhenInsertsAreNotOrdered() {
        maxPriorityQueue.insert(3);
        maxPriorityQueue.insert(20);
        maxPriorityQueue.insert(1);
        maxPriorityQueue.removeMax();
        maxPriorityQueue.insert(12);
        maxPriorityQueue.insert(-6);
        assertThat(maxPriorityQueue.removeMax(), is(12));
    }

}