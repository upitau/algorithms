package com.upit.algo.symboltable;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LinearProbingHashSymbolTableTest extends AbstractSymbolTableTest {

    public LinearProbingHashSymbolTableTest() {
        symbolTable = new LinearProbingHashSymbolTable<>(20);
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(new LinearProbingHashSymbolTable<>(4).isEmpty(), is(true));
    }

}