package com.upit.algo.symboltable;

import java.util.Arrays;
import java.util.Iterator;

public class ArraySymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K,V> {
    private static int MIN_SIZE = 16;

    private Entry<K, V>[] items = (Entry<K, V>[]) new Entry[MIN_SIZE];
    private int size = 0;

    @Override
    public void put(K key, V value) {
        if (size == items.length) {
            items = Arrays.copyOf(items, size * 2);
        }
        int rank = rank(key);
        if (rank >= size || !key.equals(items[rank].key)) {
            for (int i = size - 1; i >= rank; i--) {
                items[i + 1] = items[i];
            }
            size++;
        }
        items[rank] = new Entry<>(key, value);
    }

    @Override
    public V get(K key) {
        int rank = rank(key);
        if (rank < size && key.equals(items[rank].key)) {
            return items[rank].value;
        }
        return null;
    }

    @Override
    public void delete(K key) {
        int rank = rank(key);
        if (rank < size && key.equals(items[rank].key)) {
            for (int i = rank; i < size; i++) {
                items[i] = items[i + 1];
            }
            items[--size] = null;

            shrinkItems();
        }
    }

    private void shrinkItems() {
        if (size <= items.length / 4 && size > MIN_SIZE) {
            items = Arrays.copyOf(items, size / 2);
        }
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public K min() {
        checkNotEmpty();
        return items[0].key;
    }

    @Override
    public K max() {
        checkNotEmpty();
        return items[size - 1].key;
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty symbol table");
        }
    }

    @Override
    public K floor(K key) {
        if (isEmpty()) {
            return null;
        }

        int rank = rank(key);
        if (rank < size && key.equals(items[rank].key)) {
            return key;
        } else if (rank == 0) {
            return null;
        } else {
            return items[rank - 1].key;
        }
    }

    @Override
    public K ceiling(K key) {
        if (isEmpty()) {
            return null;
        }

        int rank = rank(key);
        return rank < size ? items[rank].key : null;
    }

    @Override
    public int rank(K key) {
        int lo = 0;
        int hi = size - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(items[mid].key);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    @Override
    public K select(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("There are not item with index: " + index);
        }

        return items[index].key;
    }

    @Override
    public void deleteMin() {
        for (int i = 1; i < size; i++) {
            items[i - 1] = items[i];
        }
        deleteMax();
    }

    @Override
    public void deleteMax() {
        items[--size] = null;
        shrinkItems();
        }

    @Override
    public int size(K lo, K hi) {
        checkNotEmpty();

        K from = ceiling(lo);
        int fromIndex = from == null ? size : rank(from);
        K to = floor(hi);
        int toIndex = to == null ? 0 : rank(to);

        return Math.max(0, toIndex - fromIndex + 1);
    }

    public Iterable<K> keys(K lo, K hi) {
        checkNotEmpty();

        K from = ceiling(lo);
        int fromIndex = from == null ? size : rank(from);
        K to = floor(hi);
        int toIndex = to == null ? -1 : rank(to);

        return new ItemIterator(fromIndex, toIndex);
    }

    public Iterable<K> keys() {
        checkNotEmpty();
        return new ItemIterator(0, size - 1);
    }


    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    private class ItemIterator implements Iterable<K> {
        private int from;
        private int to;

        public ItemIterator(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                private int current = from;

                @Override
                public boolean hasNext() {
                    return current <= to;
                }

                @Override
                public K next() {
                    if (!hasNext()) {
                        throw new IllegalStateException("Iterator is empty");
                    }
                    return items[current++].key;
                }
            };
        }
    }
}
