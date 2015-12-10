package com.upit.algo.compression;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BitStreamTest {
    private BitStream bits = new BitStream();

    @Test
    public void shouldAddBit() {
        bits.add(false);
        bits.add(true);

        assertThat(bits.size(), is(2));
        assertThat(bits.get(0), is(false));
        assertThat(bits.get(1), is(true));
    }

    @Test
    public void shouldAddSequenceOfBit() {
        bits.addSequence(true, 2);

        assertThat(bits.size(), is(2));
        assertThat(bits.get(0), is(true));
        assertThat(bits.get(1), is(true));
    }

    @Test
    public void shouldAddChunkOfBits() {
        bits.add(5, 4);

        assertThat(bits.size(), is(4));
        assertThat(bits.get(0), is(false));
        assertThat(bits.get(1), is(true));
        assertThat(bits.get(2), is(false));
        assertThat(bits.get(3), is(true));
    }

    @Test
    public void shouldAddChunksOfBits() {
        bits.add(new int[]{5}, 4);

        assertThat(bits.size(), is(4));
        assertThat(bits.get(0), is(false));
        assertThat(bits.get(1), is(true));
        assertThat(bits.get(2), is(false));
        assertThat(bits.get(3), is(true));
    }

    @Test
    public void shouldDeepEqualSimilarBitStreams() {
        BitStream streamA = new BitStream();
        streamA.addSequence(true, 5);
        BitStream streamB = new BitStream();
        streamB.addSequence(true, 5);

        assertTrue(streamA.deepEquals(streamB));
    }

    @Test
    public void shouldIterate() {
        bits.add(new int[]{5}, 4);
        Iterator<Boolean> iter = bits.iterator();

        assertThat(iter.next(), is(false));
        assertThat(iter.next(), is(true));
        assertThat(iter.next(), is(false));
        assertThat(iter.next(), is(true));
        assertThat(iter.hasNext(), is(false));
    }

}