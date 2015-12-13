package com.upit.algo.compression;

import org.junit.Test;

import static org.junit.Assert.*;

public class LZWCompressionTest {
    private LZWCompression compression = new LZWCompression();

    @Test
    public void shouldCompress() {
        BitStream rawBits = new BitStream();
        rawBits.add(new int[]{'a', 'b', 'a', 'b', 'a', 'c', 'a'}, 8);

        assertTrue(compression.compress(rawBits).deepEquals(createCompressedAbababa()));
    }

    @Test
    public void shouldDecompress() {
        BitStream expected = new BitStream();
        expected.add(new int[]{'a', 'b', 'a', 'b', 'a', 'c', 'a'}, 8);

        assertTrue(compression.expand(createCompressedAbababa()).deepEquals(expected));
    }

    @Test
    public void shouldDecompressCompressed() {
        BitStream rawBits = new BitStream();
        rawBits.add(new int[]{'a', 'b', 'r', 'a', 'c', 'a', 'd', 'a', 'b', 'r', 'a', '!'}, 8);
        assertTrue(compression.expand(compression.compress(rawBits)).deepEquals(rawBits));
    }

    private BitStream createCompressedAbababa() {
        BitStream compressedAbabaca = new BitStream();

        compressedAbabaca.add('a', 8);
        compressedAbabaca.add('b', 8);
        compressedAbabaca.add(0x81, 8);
        compressedAbabaca.add('a', 8);
        compressedAbabaca.add('c', 8);
        compressedAbabaca.add('a', 8);
        compressedAbabaca.add(0x80, 8);

        return compressedAbabaca;
    }

}