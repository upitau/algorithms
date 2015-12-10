package com.upit.algo.compression;

public class RunLengthEncoding {
    private static final int MAX_LENGTH = 255;
    private static final int MAX_BITS = 8;

    public BitStream compress(BitStream rawBits) {
        BitStream compressedBits = new BitStream();
        boolean expectedBit = false;
        int expectedBitsLength = 0;
        for (boolean bit: rawBits) {
            if (bit != expectedBit) {
                compressedBits.add(expectedBitsLength, MAX_BITS);
                expectedBitsLength = 0;
                expectedBit = !expectedBit;
            }

            expectedBitsLength++;

            if (expectedBitsLength == MAX_LENGTH) {
                compressedBits.add(expectedBitsLength, MAX_BITS);
                expectedBitsLength = 0;
                expectedBit = !expectedBit;
            }
        }

        if (expectedBitsLength > 0) {
            compressedBits.add(expectedBitsLength, MAX_BITS);
        }

        return compressedBits;
    }

    public BitStream expand(BitStream compressedBits) {
        BitStream expanded = new BitStream();

        boolean currentBit = false;
        int currentLength = 0;
        int count = 0;
        for (boolean bit: compressedBits) {
            currentLength <<= 1;
            if (bit) {
                currentLength++;
            }
            count++;

            if (count == MAX_BITS) {
                expanded.addSequence(currentBit, currentLength);
                currentBit = !currentBit;
                currentLength = 0;
                count = 0;
            }
        }

        if (count > 0) {
            expanded.addSequence(currentBit, currentLength);
        }

        return expanded;
    }
}
