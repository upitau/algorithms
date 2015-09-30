package com.upit.algo.unionfind;

import java.util.Arrays;

public class WeightedQuickUnion implements UnionFind {
    private int parents[];
    private int sizes[];

    public WeightedQuickUnion(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        sizes = new int[n];
        Arrays.fill(sizes, 1);
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);

        if (pRoot == qRoot) {
            return;
        }

        if (sizes[pRoot] < sizes[qRoot]) {
            parents[pRoot] = qRoot;
            sizes[qRoot] += sizes[pRoot];
        } else {
            parents[qRoot] = pRoot;
            sizes[pRoot] += sizes[qRoot];
        }
    }

    private int root(int x) {
        while (x != parents[x]) {
            x = parents[x];
        }
        return x;
    }
}
