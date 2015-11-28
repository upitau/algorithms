package com.upit.algo.symboltable;

public class Trie<V> {
    private static final int RADIX = 256;

    private Node root = new Node();

    public void put(String key, V value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node node, String key, V value, int charIndex) {
        if (node == null) {
            node = new Node();
        }

        if (key.length() == charIndex) {
            node.value = value;
        } else {
            int ch = key.charAt(charIndex);
            node.next[ch] = put(node.next[ch], key, value, charIndex + 1);
        }
        return node;
    }

    public V get(String key) {
        Node node = get(root, key, 0);
        return node == null ? null : (V) node.value;
    }

    private Node get(Node node, String key, int charIndex) {
        if (node == null) {
            return null;
        }

        if (key.length() == charIndex) {
            return node;
        } else {
            int ch = key.charAt(charIndex);
            return get(node.next[ch], key, charIndex + 1);
        }
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void delete(String key) {
        delete(root, key, 0);
    }

    private Node delete(Node node, String key, int charIndex) {
        if (node == null) {
            return null;
        }

        if (key.length() == charIndex) {
            node.value = null;
        } else {
            int ch = key.charAt(charIndex);
            node.next[ch] = delete(node.next[ch], key, charIndex + 1);
        }

        return node.isRedundant() ? null : node;
    }


    private static class Node {
        private Object value;
        private Node[] next = new Node[RADIX];

        public boolean isRedundant() {
            return value == null && !hasNext();
        }

        private boolean hasNext() {
            for (int i = 0; i < RADIX; i++) {
                if (next[i] != null) {
                    return true;
                }
            }
            return false;
        }
    }
}
