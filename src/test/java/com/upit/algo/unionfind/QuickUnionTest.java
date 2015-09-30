package com.upit.algo.unionfind;

public class QuickUnionTest extends AbstractUnionFindTest {

    @Override
    protected UnionFind createUnionFind(int n) {
        return new QuickUnion(n);
    }
}
