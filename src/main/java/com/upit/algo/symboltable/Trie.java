package com.upit.algo.symboltable;

import com.upit.algo.queue.ArrayQueue;
import com.upit.algo.queue.Queue;

public class Trie<V> implements StringSymbolTable<V> {
    private static final int RADIX = 256;

    private Node root = new Node();

    @Override
    public void put(String key, V value) {
        requireNotEmpty(key);
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

    @Override
    public V get(String key) {
        requireNotEmpty(key);
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

    @Override
    public boolean contains(String key) {
        requireNotEmpty(key);
        return get(key) != null;
    }

    @Override
    public void delete(String key) {
        requireNotEmpty(key);
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

    @Override
    public Iterable<String> keys() {
        Queue<String> queue = new ArrayQueue<>();
        collect(root, "", queue);
        return queue;
    }

    @Override
    public Iterable<String> keysWithPrefix(String prefix) {
        requireNotEmpty(prefix);
        ArrayQueue<String> queue = new ArrayQueue<>();
        Node prefixNode = get(root, prefix, 0);
        collect(prefixNode, prefix, queue);
        return queue;
    }

    private void collect(Node node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }

        if (node.value != null) {
            queue.enqueue(prefix);
        }

        for (char ch = 0; ch < RADIX; ch++) {
            collect(node.next[ch], prefix + ch, queue);
        }
    }

    @Override
    public String longestPrefixOf(String query) {
        requireNotEmpty(query);
        String longestPrefix = longestPrefixOf(root, query, 0);
        return longestPrefix != null ? longestPrefix : "";
    }

    private String longestPrefixOf(Node node, String s, int index) {
        if (node == null) {
            return null;
        }

        String nodePath = node.value != null ? s.substring(0, index) : null;
        if (index == s.length()) {
            return nodePath;
        }

        char ch = s.charAt(index);
        String longestPrefix = longestPrefixOf(node.next[ch], s, index + 1);
        return longestPrefix != null ? longestPrefix : nodePath;
    }

    private void requireNotEmpty(String key) {
        if ("".equals(key)) {
            throw new IllegalArgumentException(key);
        }
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
