package com.upit.algo.unionfind;

public class WeightedQuickUnionWithPathCompressionTest extends AbstractUnionFindTest {

    @Override
    protected UnionFind createUnionFind(int n) {
        return new WeightedQuickUnionWithPathCompression(n);
    }
}
