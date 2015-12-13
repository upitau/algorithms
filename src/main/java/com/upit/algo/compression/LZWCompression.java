package com.upit.algo.compression;

import com.upit.algo.symboltable.Trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple implementation that does not handle special case (abababa)
 */
public class LZWCompression {
    private static final int RADIX = 128;
    private static final int CODE_LIMIT = 256;
    private static final int CHAR_LENGTH = 8;

    public BitStream compress(BitStream rawBits) {
        checkLength(rawBits);

        Trie<Character> trie = new Trie<>();
        for (char ch = 0; ch < RADIX; ch++) {
            trie.put("" + ch, ch);
        }
        char code = RADIX + 1;

        String chars = toString(rawBits);
        BitStream compressedBits = new BitStream();

        while (chars.length() > 0) {
            String s = trie.longestPrefixOf(chars);
            compressedBits.add(trie.get(s), CHAR_LENGTH);
            int l = s.length();
            if (l < chars.length() && code < CODE_LIMIT) {
                trie.put(chars.substring(0, l + 1), code++);
            }
            chars = chars.substring(l);
        }

        compressedBits.add(RADIX, CHAR_LENGTH);

        return compressedBits;
    }

    public BitStream expand(BitStream compressedBits) {
        checkLength(compressedBits);

        Map<Integer, int[]> bitsByCode = new HashMap<>();
        for (int i = 0; i < RADIX; i++) {
            bitsByCode.put(i, new int[]{i});
        }
        int code = RADIX + 1;

        int[] codes = toCodes(compressedBits);
        BitStream rawBits = new BitStream();

        int[] prevChunk = bitsByCode.get(codes[0]);
        rawBits.add(prevChunk, CHAR_LENGTH);
        for (int i = 1; i < codes.length - 1; i++) {
            int[] chunk = bitsByCode.get(codes[i]);
            rawBits.add(chunk, CHAR_LENGTH);

            if (code < CODE_LIMIT) {
                int[] bits = Arrays.copyOf(prevChunk, prevChunk.length + 1);
                bits[prevChunk.length] = chunk[0];
                bitsByCode.put(code++, bits);
            }

            prevChunk = chunk;
        }

        return rawBits;
    }

    private void checkLength(BitStream bits) {
        if (bits.size() % CHAR_LENGTH != 0) {
            throw new IllegalArgumentException("Incorrect length of bit stream: " + bits.size());
        }
    }

    private String toString(BitStream bits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bits.size(); i += CHAR_LENGTH) {
            sb.append((char) bits.getChunk(i, CHAR_LENGTH));
        }

        return sb.toString();
    }

    private int[] toCodes(BitStream bits) {
        int[] codes = new int[bits.size() / CHAR_LENGTH];
        for (int i = 0; i < codes.length; i++) {
            codes[i] = bits.getChunk(i * CHAR_LENGTH, CHAR_LENGTH);
        }

        return codes;
    }
}
