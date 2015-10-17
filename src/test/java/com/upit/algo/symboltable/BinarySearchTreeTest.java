package com.upit.algo.symboltable;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BinarySearchTreeTest extends AbstractOrderedSymbolTableTest {

    public BinarySearchTreeTest() {
        symbolTable = new BinarySearchTree<>();
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(new BinarySearchTree<>().isEmpty(), is(true));
    }
}