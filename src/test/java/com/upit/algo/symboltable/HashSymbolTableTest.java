package com.upit.algo.symboltable;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HashSymbolTableTest extends AbstractSymbolTableTest {

    public HashSymbolTableTest() {
        symbolTable = new HashSymbolTable<>(4);
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(new HashSymbolTable<>(4).isEmpty(), is(true));
    }

}