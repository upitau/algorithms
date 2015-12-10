package com.upit.algo.compression;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RunLengthEncodingTest {
    private RunLengthEncoding encoding = new RunLengthEncoding();
    private BitStream rawBits = new BitStream();

    @Test
    public void shouldCompressAndDecompressSequenceOfZeros() {
        rawBits.addSequence(false, 10);

        BitStream compressedBits = new BitStream();
        compressedBits.add(10, 8);

        assertTrue(encoding.compress(rawBits).deepEquals(compressedBits));
        assertTrue(encoding.expand(compressedBits).deepEquals(rawBits));
    }

    @Test
    public void shouldCompressAndDecompressLongSequenceOfZeros() {
        rawBits.addSequence(false, 1000);

        BitStream compressedBits = new BitStream();
        compressedBits.add(new int[]{255, 0, 255, 0, 255, 0, 235}, 8);

        assertTrue(encoding.compress(rawBits).deepEquals(compressedBits));
        assertTrue(encoding.expand(compressedBits).deepEquals(rawBits));
    }

    @Test
    public void shouldCompressAndDecompressSequenceOfOnes() {
        rawBits.addSequence(true, 10);

        BitStream compressedBits = new BitStream();
        compressedBits.add(new int[]{0, 10}, 8);

        assertTrue(encoding.compress(rawBits).deepEquals(compressedBits));
        assertTrue(encoding.expand(compressedBits).deepEquals(rawBits));
    }

    @Test
    public void shouldCompressAndDecompressMixOfZerosAndOnes() {
        rawBits.addSequence(true, 20);
        rawBits.addSequence(false, 40);
        rawBits.addSequence(true, 40);

        BitStream compressedBits = new BitStream();
        compressedBits.add(new int[]{0, 20, 40, 40}, 8);

        assertTrue(encoding.compress(rawBits).deepEquals(compressedBits));
        assertTrue(encoding.expand(compressedBits).deepEquals(rawBits));
    }

}