package com.upit.algo.compression;

import com.upit.algo.queue.MinPriorityQueue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HuffmanCompression {

    public BitStream expand(BitStream compressedBits) {
        BitStream expandedBits = new BitStream();

        Iterator<Boolean> iter = compressedBits.iterator();
        Node root = readTrie(iter);
        Node node = root;
        while (iter.hasNext()) {
            node = iter.next() ? node.right : node.left;
            if (node.isLeaf()) {
                expandedBits.add(node.ch, 8);
                node = root;
            }
        }
        return expandedBits;
    }

    private Node readTrie(Iterator<Boolean> iter) {
        if (iter.next()) {
            char ch = 0;
            for (int i = 0; i < 8; i++) {
                ch <<= 1;
                if (iter.next()) {
                    ch++;
                }
            }
            return new Node(ch, 0, null, null);
        } else {
            return new Node('\0', 0, readTrie(iter), readTrie(iter));
        }
    }

    public BitStream compress(BitStream rawBits) {
        if (rawBits.size() % 8 != 0) {
            throw new IllegalArgumentException("Incorrect length of bit stream: " + rawBits.size());
        }

        char[] chars = new char[rawBits.size() / 8];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) rawBits.getChunk(i * 8, 8);
        }

        Map<Character, Node> freqByChar = new HashMap<>();
        for (char ch: chars) {
            if (!freqByChar.containsKey(ch)) {
                freqByChar.put(ch, new Node(ch, 0, null, null));
            }
            freqByChar.get(ch).freq++;
        }

        BitStream compressedBits = new BitStream();
        writeTrie(compressedBits, buildTrie(freqByChar.values()));

        for (char ch: chars) {
            freqByChar.get(ch).addTo(compressedBits);
        }

        return compressedBits;
    }

    private Node buildTrie(Iterable<Node> nodes) {

        MinPriorityQueue<Node> minPQ = new MinPriorityQueue<>();
        for (Node node: nodes) {
            minPQ.insert(node);
        }

        while (minPQ.getSize() > 1) {
            Node node1 = minPQ.removeMin();
            node1.addEncodingPrefix(false);
            Node node2 = minPQ.removeMin();
            node2.addEncodingPrefix(true);
            Node parentNode = new Node('\0', node1.freq + node2.freq, node1, node2);
            minPQ.insert(parentNode);
        }
        return minPQ.removeMin();
    }

    private void writeTrie(BitStream bits, Node node) {
        if (node.isLeaf()) {
            bits.add(true);
            bits.add(node.ch, 8);
        } else {
            bits.add(false);
            writeTrie(bits, node.left);
            writeTrie(bits, node.right);
        }
    }


    private static class Node implements Comparable<Node> {
        private char ch;
        private int freq;
        private Node left;
        private Node right;
        private int encoding;
        private int encodingLength;

        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public void addEncodingPrefix(boolean bit) {
            if (isLeaf()) {
                if (bit) {
                    encoding |= 1 << encodingLength;
                }
                encodingLength++;
            } else {
                left.addEncodingPrefix(bit);
                right.addEncodingPrefix(bit);
            }
        }

        public void addTo(BitStream bits) {
            bits.add(encoding, encodingLength);
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

}
