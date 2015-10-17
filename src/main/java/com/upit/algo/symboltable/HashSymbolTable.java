package com.upit.algo.symboltable;

import java.util.ArrayList;
import java.util.List;

public class HashSymbolTable<K, V> implements SymbolTable<K, V> {
    private Node[] table;
    private int size = 0;

    public HashSymbolTable(int size) {
        table = new Node[size];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x0fffffff) % table.length;
    }

    public void put(K key, V value) {
        int hash = hash(key);
        Node node = getNode(hash(key), key);
        if (node != null) {
            node.value = value;
        } else {
            table[hash] = new Node(key, value, table[hash]);
            size++;
        }
    }

    public V get(K key) {
        Node node = getNode(hash(key), key);
        return node != null ? (V) node.value : null;
    }

    private Node getNode(int hash, K key) {
        for (Node node = table[hash]; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        int hash = hash(key);
        Node parent = table[hash];
        if (parent != null) {
            if (parent.key == key) {
                table[hash] = parent.next;
                size--;
            } else {
                for (Node node = parent.next; node != null; parent = node, node = node.next) {
                    if (node.key.equals(key)) {
                        parent.next = node.next;
                        size--;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(hash(key), key) != null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            for (Node node = table[i]; node != null; node = node.next) {
                keys.add((K) node.key);
            }
        }
        return keys;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private static class Node {
        private Object key;
        private Object value;
        private Node next;

        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

}
