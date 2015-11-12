package com.upit.algo.other;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IndexMinPriorityQueue<K extends Comparable<K>> {
    private static int UNDEFINED = -1;

    private K[] keys;
    private int[] pq;
    private int[] qp;
    private int size;

    public IndexMinPriorityQueue(int maxSize) {
        keys = (K[]) new Comparable[maxSize];
        pq = new int[maxSize];
        qp = new int[maxSize];
        Arrays.fill(qp, UNDEFINED);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(int index, K key) {
        if (contains(index)) {
            throw new IllegalArgumentException("Element with this index already exists: " + index);
        }

        keys[index] = key;
        pq[size] = index;
        qp[index] = size;
        swim(size);
        size++;
    }

    private void swim(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (less(parent, index)) {
                break;
            }

            swap(parent, index);
            index = parent;
        }
    }

    public void decreaseKey(int index, K key) {
        if (!contains(index)) {
            throw new IllegalArgumentException("Element with this index does not exist: " + index);
        }
        if (keys[index].compareTo(key) <= 0) {
            throw new IllegalArgumentException("Key must be strictly less than existing one");
        }

        keys[index] = key;
        swim(qp[index]);
    }

    public boolean contains(int index) {
        checkInRange(index);
        return qp[index] != UNDEFINED;
    }

    public int minIndex() {
        if (size == 0) throw new NoSuchElementException("Priority queue underflow");
        return pq[0];
    }

    public K minKey() {
        return keys[minIndex()];
    }

    public K keyOf(int index) {
        if (!contains(index)) {
            throw new IllegalArgumentException("Element with this index does not exist: " + index);
        }
        return keys[index];
    }

    public int removeMin() {
        checkNotEmpty();
        int min = pq[0];
        swap(0, --size);
        sink(0);
        keys[min] = null;
        qp[min] = UNDEFINED;
        return min;
    }

    private void checkInRange(int index) {
        if (index < 0 || index >= pq.length) {
            throw new IndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
    }

    private void sink(int index) {
        while (firstChild(index) < size) {
            int child = firstChild(index);
            if (child + 1 < size && less(child + 1, child)) {
                child++;
            }

            if (less(child, index)) {
                swap(index, child);
                index = child;
            } else {
                break;
            }
        }
    }

    private int firstChild(int index) {
        return index * 2 + 1;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

}
