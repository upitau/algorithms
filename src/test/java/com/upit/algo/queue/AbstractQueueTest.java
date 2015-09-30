package com.upit.algo.queue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractQueueTest {
    protected Queue<String> queue;

    @Test
    public void shouldCopyShortSequence() {
        assertThat(executeOnQueue("12345-----"), is("12345"));
    }

    @Test
    public void shouldCopyLongSequence() {
        assertThat(executeOnQueue("abcdefghijklmnopqrstuvwxyz--------------------------"), is("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void shouldCopySequenceWithDequeueInTheMiddle() {
        assertThat(executeOnQueue("abcde---12345fghijk-------------"), is("abcde12345fghijk"));
    }

    @Test
    public void shouldIterateAllItems() {
        executeOnQueue("1234567890abcde");
        StringBuilder sb = new StringBuilder();
        for (String item: queue) {
            sb.append(item);
        }
        assertThat(sb.toString(), is("1234567890abcde"));
    }

    private String executeOnQueue(String items) {
        StringBuilder sb = new StringBuilder();
        for (String item: items.split("")) {
            if (item.equals("-")) {
                sb.append(queue.dequeue());
            } else {
                queue.enqueue(item);
            }
        }
        return sb.toString();
    }

}