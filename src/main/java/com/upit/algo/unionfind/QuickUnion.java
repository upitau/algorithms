package com.upit.algo.unionfind;

public class QuickUnion implements UnionFind {
    private int parents[];

    public QuickUnion(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        parents[root(p)] = root(q);
    }

    private int root(int x) {
        while (x != parents[x]) {
            x = parents[x];
        }
        return x;
    }
}
