package com.upit.algo.unionfind;

public class QuickFindTest extends AbstractUnionFindTest {
    @Override
    protected UnionFind createUnionFind(int n) {
        return new QuickFind(n);
    }
}
