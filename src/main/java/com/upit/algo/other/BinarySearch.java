package com.upit.algo.other;

public class BinarySearch {

    /**
     * Binary search on sorted array.
     *
     * @param sortedHaystack
     * @param needle
     * @return index of needle or -1 if not found
     */
    public int find(int[] sortedHaystack, int needle) {
        int low = 0;
        int high = sortedHaystack.length - 1;

        while (low <= high) {
            int middle = (high + low) / 2;
            int middleValue = sortedHaystack[middle];
            if (needle == middleValue) {
                return middle;
            } else if (needle < middleValue) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }

        return -1;
    }
}
