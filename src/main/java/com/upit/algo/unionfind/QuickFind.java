package com.upit.algo.unionfind;

public class QuickFind implements UnionFind {
    private int[] parents;

    public QuickFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return parents[p] == parents[q];
    }

    @Override
    public void union(int p, int q) {
        int pClass = parents[p];
        int qClass = parents[q];
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == pClass) {
                parents[i] = qClass;
            }
        }
    }
}
