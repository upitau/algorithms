package com.upit.algo.compression;

import org.junit.Test;

import static org.junit.Assert.*;

public class HuffmanCompressionTest {
    private HuffmanCompression compression = new HuffmanCompression();

    @Test
    public void shouldCompress() {
        BitStream rawBits = new BitStream();
        rawBits.add(new int[]{'a', 'b', 'a', 'b', 'a', 'c', 'a'}, 8);

        assertTrue(compression.compress(rawBits).deepEquals(createCompressedAbabaca()));
    }

    @Test
    public void shouldDecompress() {
        BitStream expected = new BitStream();
        expected.add(new int[]{'a', 'b', 'a', 'b', 'a', 'c', 'a'}, 8);

        assertTrue(compression.expand(createCompressedAbabaca()).deepEquals(expected));
    }

    @Test
    public void shouldDecompressCompressed() {
        BitStream rawBits = new BitStream();
        rawBits.add(new int[]{'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a', '!'}, 8);
        assertTrue(compression.expand(compression.compress(rawBits)).deepEquals(rawBits));
    }

    private BitStream createCompressedAbabaca() {
        BitStream compressedAbabaca = new BitStream();

        compressedAbabaca.add(false);
        compressedAbabaca.add(false);
        compressedAbabaca.add(true);
        compressedAbabaca.add('c', 8);
        compressedAbabaca.add(true);
        compressedAbabaca.add('b', 8);
        compressedAbabaca.add(true);
        compressedAbabaca.add('a', 8);

        compressedAbabaca.add(0x2D9, 10);

        return compressedAbabaca;
    }

}