package com.upit.algo.symboltable;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashSymbolTable<K, V> implements SymbolTable<K, V> {
    private K TOMBSTONE = (K) new Object();

    private K[] keys;
    private V[] values;
    private int size = 0;

    public LinearProbingHashSymbolTable(int size) {
        keys = (K[]) new Object[size];
        values = (V[]) new Object[size];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x0fffffff) % keys.length;
    }

    public void put(K key, V value) {
        int i;
        for (i = hash(key); !isVacant(i); i = ++i % keys.length) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        size++;
    }

    private boolean isVacant(int index) {
        return keys[index] == null || keys[index] == TOMBSTONE;
    }

    public V get(K key) {
        for (int i = hash(key); !isVacant(i); i = ++i % keys.length) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void delete(K key) {
        for (int i = hash(key); !isVacant(i); i = ++i % keys.length) {
            if (keys[i].equals(key)) {
                keys[i] = TOMBSTONE;
                size--;
                return;
            }
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        for (int i = 0; i < this.keys.length; i++) {
            if (!isVacant(i)) {
                keys.add(this.keys[i]);
            }
        }
        return keys;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
