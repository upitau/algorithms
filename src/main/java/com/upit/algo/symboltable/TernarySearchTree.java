package com.upit.algo.symboltable;

import com.upit.algo.queue.ArrayQueue;
import com.upit.algo.queue.Queue;

public class TernarySearchTree<V> implements StringSymbolTable<V> {

    private Node root;

    @Override
    public void put(String key, V value) {
        requireNotEmpty(key);
        root = put(root, key, value, 0);
    }

    private Node put(Node node, String key, V value, int index) {
        char ch = key.charAt(index);
        if (node == null) {
            node = new Node(ch);
        }

        if (ch < node.ch) {
            node.left = put(node.left, key, value, index);
        } else if (ch > node.ch) {
            node.right = put(node.right, key, value, index);
        } else if (index < key.length() - 1) {
            node.mid = put(node.mid, key, value, index + 1);
        } else {
            node.value = value;
        }

        return node;
    }

    @Override
    public V get(String key) {
        requireNotEmpty(key);
        Node node = get(root, key, 0);
        return node == null ? null : (V) node.value;
    }

    private Node get(Node node, String key, int index) {
        if (node == null) {
            return null;
        }

        char ch = key.charAt(index);
        if (ch < node.ch) {
            return get(node.left, key, index);
        } else if (ch > node.ch) {
            return get(node.right, key, index);
        } else if (index < key.length() - 1) {
            return get(node.mid, key, index + 1);
        } else {
            return node;
        }
    }

    @Override
    public void delete(String key) {
        requireNotEmpty(key);
        root = delete(root, key, 0);
    }

    private Node delete(Node node, String key, int index) {
        if (node == null) {
            return null;
        }

        char ch = key.charAt(index);
        if (ch < node.ch) {
            node.left = delete(node.left, key, index);
        } else if (ch > node.ch) {
            node.right = delete(node.right, key, index);
        } else if (index < key.length() - 1) {
            node.mid = delete(node.mid, key, index + 1);
        } else {
            node.value = null;
        }

        return node.isEmpty() ? null : node;
    }

    @Override
    public boolean contains(String key) {
        requireNotEmpty(key);
        return get(key) != null;
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
        if (prefixNode != null) {
            if (prefixNode.value != null) {
                queue.enqueue(prefix);
            }
            collect(prefixNode.mid, prefix, queue);
        }
        return queue;
    }

    private void collect(Node node, String prefix, Queue<String> queue) {
        if (node == null) {
            return;
        }

        if (node.value != null) {
            queue.enqueue(prefix + node.ch);
        }

        collect(node.left, prefix, queue);
        collect(node.mid, prefix + node.ch, queue);
        collect(node.right, prefix, queue);
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

        String nodePath = node.value != null ? s.substring(0, index + 1) : null;
        if (index == s.length() - 1) {
            return nodePath;
        }

        String longestPrefix;
        char ch = s.charAt(index);
        if (ch < node.ch) {
            longestPrefix = longestPrefixOf(node.left, s, index);
        } else if (ch > node.ch) {
            longestPrefix = longestPrefixOf(node.right, s, index);
        } else {
            longestPrefix = longestPrefixOf(node.mid, s, index + 1);
        }

        return longestPrefix != null ? longestPrefix : nodePath;
    }

    private void requireNotEmpty(String key) {
        if ("".equals(key)) {
            throw new IllegalArgumentException(key);
        }
    }


    private static class Node {
        private Object value;
        private char ch;
        private Node left;
        private Node mid;
        private Node right;

        public Node(char ch) {
            this.ch = ch;
        }

        public boolean isEmpty() {
            return value == null
                    && left == null
                    && mid == null
                    && right == null;
        }
    }
}
