package com.upit.algo.stack;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractStackTest {
    protected Stack<String> stack;

    @Test
    public void shouldReverseShortSequence() {
        assertThat(executeOnStack("12345-----"), is("54321"));
    }

    @Test
    public void shouldReverseLongSequence() {
        assertThat(executeOnStack("abcdefghijklmnopqrstuvwxyz--------------------------"), is("zyxwvutsrqponmlkjihgfedcba"));
    }

    @Test
    public void shouldReverseSequenceWithPopInTheMiddle() {
        assertThat(executeOnStack("abcde---12345fghijk-------------"), is("edckjihgf54321ba"));
    }

    @Test
    public void shouldIterateAllItems() {
        executeOnStack("1234567890abcde");
        StringBuilder sb = new StringBuilder();
        for (String item: stack) {
            sb.append(item);
        }
        assertThat(sb.toString(), is("edcba0987654321"));
    }

    private String executeOnStack(String items) {
        StringBuilder sb = new StringBuilder();
        for (String item: items.split("")) {
            if (item.equals("-")) {
                sb.append(stack.pop());
            } else {
                stack.push(item);
            }
        }
        return sb.toString();
    }

}