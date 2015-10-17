package com.upit.algo.symboltable;

import com.upit.algo.queue.ArrayQueue;
import com.upit.algo.queue.Queue;

public class RedBlackTree<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private static boolean RED = true;
    private static boolean BLACK = false;

    private Node<K, V> root;

    public void put(K key, V value) {
        root = put(root, key, value);
        root.colour = BLACK;
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
//        node.size = size(node.left) + size(node.right) + 1;
        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            node = flipColour(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        rightChild.colour = node.colour;
        node.colour = RED;
        return rightChild;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        leftChild.colour = node.colour;
        node.colour = RED;
        return leftChild;
    }

    private Node<K, V> flipColour(Node<K, V> node) {
        node.left.colour = BLACK;
        node.right.colour = BLACK;
        node.colour = RED;
        return node;
    }

    public V get(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    public void delete(K key) {
//        root = delete(root, key);
    }

    private Node<K, V> delete(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else  {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<K, V> substitution = min(node.right);
                substitution.right = deleteMin(node.right);
                substitution.left = node.left;
                node = substitution;
            }
        }
        node.size = size(node.left) + 1 + size(node.right);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
//        return size(root);
        return 0;
    }

    private int size(Node<K, V> node) {
//        return node == null ? 0 : node.size;
        return 0;
    }

    @Override
    public K min() {
        Node<K, V> minNode = min(root);
        return minNode == null ? null : minNode.key;
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        return node.left == null ? node : min(node.left);
    }

    @Override
    public K max() {
        if (isEmpty()) {
            return null;
        }

        Node<K, V> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    @Override
    public K floor(K key) {
        K floor = null;
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                floor = node.key;
                node = node.right;
            } else {
                return key;
            }
        }
        return floor;
    }

    @Override
    public K ceiling(K key) {
        K ceiling = null;
        Node<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                ceiling = node.key;
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return key;
            }
        }
        return ceiling;
    }

    @Override
    public int rank(K key) {
//        return rank(root, key);
        return 0;
    }

    private int rank(Node<K, V> node, K key) {
        if (node == null) {
            return 0;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(node.left, key);
        } else if (cmp > 0) {
            return size(node.left) + 1 + rank(node.right, key);
        } else {
            return size(node.left);
        }
    }

    @Override
    public K select(int index) {
//        Node<K, V> node = select(root, index);
//        return node == null ? null : node.key;
        return null;
    }

    private Node<K, V> select(Node<K, V> node, int index) {
        if (node == null) {
            return null;
        }

        int cmp = index - size(node.left);
        if (cmp < 0) {
            return select(node.left, index);
        } else if (cmp > 0) {
            return select(node.right, index - size(node.left) - 1);
        } else {
            return node;
        }
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) {
            return;
        }

        root = deleteMin(root);
        if (!isEmpty()) {
            root.colour = BLACK;
        }
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) {
            return null;
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            return node.right;
        } else {
            node.left = deleteMin(node.left);
            node.size = size(node.left) + 1 + size(node.right);
            return node;
        }

    }

    @Override
    public void deleteMax() {
//        root = deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node.left;
        } else {
            node.right = deleteMax(node.right);
            node.size = size(node.left) + 1 + size(node.right);
            return node;
        }
    }

    @Override
    public int size(K lo, K hi) {
//        if (hi.compareTo(lo) < 0) {
//            throw new IllegalArgumentException(String.format("hi (%s) is less then lo (%s)", hi, lo));
//        }
//        K from = ceiling(lo);
//        K to = floor(hi);
//        return (from == null || to == null) ? 0 : Math.max(0, rank(to) - rank(from) + 1) ;
        return 0;
    }

    @Override
    public Iterable<K> keys() {
        Queue<K> queue = new ArrayQueue<>();
        addKeysInOrder(queue, root, min(), max());
        return queue;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new ArrayQueue<>();
        addKeysInOrder(queue, root, ceiling(lo), floor(hi));
        return queue;
    }

    private void addKeysInOrder(Queue<K> queue, Node<K, V> node, K lo, K hi) {
        if (node == null || lo.compareTo(node.key) > 0 || hi.compareTo(node.key) < 0) {
            return;
        }

        addKeysInOrder(queue, node.left, lo, hi);
        queue.enqueue(node.key);
        addKeysInOrder(queue, node.right, lo, hi);
    }

    private boolean isRed(Node<K, V> node) {
        return node != null && node.colour == RED;
    }


    private static class Node<K extends Comparable<K>, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int size = 1;
        private boolean colour = RED;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s{%s,%s}", key, left, right);
        }
    }
}
