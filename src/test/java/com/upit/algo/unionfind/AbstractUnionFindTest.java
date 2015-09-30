package com.upit.algo.unionfind;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractUnionFindTest {
    private UnionFind unionFind;

    @Before
    public void init() throws IOException {
        unionFind = initFromFile("/union-find.txt");
    }

    @Test
    public void shouldFindWhenConnected() {
        assertThat(unionFind.connected(2, 0), is(true));
    }

    @Test
    public void shouldNotFindWhenDisconnected() {
        assertThat(unionFind.connected(9, 1), is(false));
    }

    private UnionFind initFromFile(String filename) throws IOException {
        Scanner scanner = new Scanner(getClass().getResourceAsStream(filename));
        UnionFind quickFind = createUnionFind(scanner.nextInt());
        while (scanner.hasNext()) {
            quickFind.union(scanner.nextInt(), scanner.nextInt());
        }
        return quickFind;
    }

    abstract protected UnionFind createUnionFind(int n);
}
