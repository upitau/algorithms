package com.upit.algo.symboltable;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@Ignore
public class RedBlackTreeTest extends AbstractOrderedSymbolTableTest {

    public RedBlackTreeTest() {
        symbolTable = new RedBlackTree<>();
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(new RedBlackTree<>().isEmpty(), is(true));
    }

    @Test
    public void shouldInsertWithAllRotations() {
        RedBlackTree<String, Integer> tree = new RedBlackTree<>();
        tree.put("S", 1);
        tree.put("E", 2);
        tree.put("A", 3);
        tree.put("R", 4);
        tree.put("C", 5);
        tree.put("H", 6);
        tree.put("X", 7);
        tree.put("M", 8);
        tree.put("P", 9);
        tree.put("L", 10);
        assertThat(tree.size(), is(10));
    }

}