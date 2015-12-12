package com.upit.algo.queue;

import com.upit.algo.queue.MinPriorityQueue;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MinPriorityQueueTest {
    private MinPriorityQueue minPriorityQueue = new MinPriorityQueue();

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(minPriorityQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldBeEmptyWhenAllAddedAreRemoved() {
        minPriorityQueue.insert(15);
        minPriorityQueue.removeMin();
        assertThat(minPriorityQueue.isEmpty(), is(true));
    }

    @Test
    public void shouldBeNotEmptyWhenHasItems() {
        minPriorityQueue.insert(15);
        assertThat(minPriorityQueue.isEmpty(), is(false));
    }

    @Test
    public void shouldGetMinWhenInsertsAreOrdered() {
        minPriorityQueue.insert(1);
        minPriorityQueue.insert(2);
        minPriorityQueue.insert(3);
        assertThat(minPriorityQueue.removeMin(), is(1));
    }

    @Test
    public void shouldGetMinWhenInsertsAreReversed() {
        minPriorityQueue.insert(3);
        minPriorityQueue.insert(2);
        minPriorityQueue.insert(1);
        assertThat(minPriorityQueue.removeMin(), is(1));
    }

    @Test
    public void shouldGetSecondMinWhenInsertsAreNotOrdered() {
        minPriorityQueue.insert(3);
        minPriorityQueue.insert(20);
        minPriorityQueue.insert(1);
        minPriorityQueue.insert(12);
        minPriorityQueue.insert(-6);
        minPriorityQueue.removeMin();
        assertThat(minPriorityQueue.removeMin(), is(1));
    }

}