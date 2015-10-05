package com.upit.algo.symboltable;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraySymbolTableTest extends AbstractSymbolTableTest {

    public ArraySymbolTableTest() {
        symbolTable = new ArraySymbolTable<>();
    }

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(new ArraySymbolTable<>().isEmpty(), is(true));
    }
}
