package com.upit.algo.unionfind;

public class WeightedQuickUnionTest extends AbstractUnionFindTest {

    @Override
    protected UnionFind createUnionFind(int n) {
        return new WeightedQuickUnion(n);
    }
}
