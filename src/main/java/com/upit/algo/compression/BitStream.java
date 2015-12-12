package com.upit.algo.compression;

import java.util.BitSet;
import java.util.Iterator;

public class BitStream implements Iterable<Boolean> {
    private BitSet bits = new BitSet();
    private int size;

    public boolean get(int index) {
        checkIndex(index);
        return bits.get(index);
    }

    public int getChunk(int index, int chunkSize) {
        checkIndex(index);
        checkChunkSize(chunkSize);
        if (index + chunkSize > size) {
            throw new IllegalArgumentException("Illegal index or chunk size");
        }

        int bits = 0;
        for (int i = index; i < index + chunkSize && i < size; i++) {
            bits <<= 1;
            if (get(i)) {
                bits++;
            }
        }
        return bits;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
    }

    private void checkChunkSize(int chunkSize) {
        if (chunkSize < 0 || chunkSize >= 32) {
            throw new IllegalArgumentException("Illegal chunk size: " + chunkSize);
        }
    }

    public void add(boolean bit) {
        bits.set(size++, bit);
    }

    public void addSequence(boolean bit, int length) {
        for (int i = 0; i < length; i++) {
            add(bit);
        }
    }

    public void add(int chunkOfBits, int bitsInChunk) {
        checkChunkSize(bitsInChunk);
        for (int i = 0; i < bitsInChunk; i++) {
            boolean bit = ((chunkOfBits >> (bitsInChunk - i - 1)) & 1) == 1;
            add(bit);
        }
    }

    public void add(int[] chunksOfBits, int bitsInChunk) {
        checkChunkSize(bitsInChunk);
        for (int i = 0; i < chunksOfBits.length; i++) {
            add(chunksOfBits[i], bitsInChunk);
        }
    }

    public int size() {
        return size;
    }

    public boolean deepEquals(BitStream that) {
        if (that == null || that.size != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (this.bits.get(i) != that.bits.get(i)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Iter();
    }


    private class Iter implements Iterator<Boolean> {
        private int index;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Boolean next() {
            return bits.get(index++);
        }
    }
}
