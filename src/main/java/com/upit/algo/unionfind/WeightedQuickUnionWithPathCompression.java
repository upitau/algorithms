package com.upit.algo.unionfind;

import java.util.Arrays;

public class WeightedQuickUnionWithPathCompression implements UnionFind {
    private int parents[];
    private int sizes[];

    public WeightedQuickUnionWithPathCompression(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        sizes = new int[n];
        Arrays.fill(sizes, 1);
    }

    public boolean connected(int p, int q) {
        return rootWithCompression(p) == rootWithCompression(q);
    }

    public void union(int p, int q) {
        int pRoot = rootWithCompression(p);
        int qRoot = rootWithCompression(q);

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

    private int rootWithCompression(int x) {
        int xRoot = root(x);
        compressPath(x, xRoot);
        return xRoot;
    }

    private int root(int x) {
        while (x != parents[x]) {
            x = parents[x];
        }
        return x;
    }

    private void compressPath(int x, int xRoot) {
        while (x != parents[x]) {
            int xParent = parents[x];
            parents[x] = xRoot;
            x = xParent;
        }
    }
}
