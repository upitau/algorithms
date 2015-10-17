package com.upit.algo.symboltable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractSymbolTableTest {
    protected SymbolTable<String, Integer> symbolTable;

    @Before
    /**
     * Builds the following tree:
     *              G
     *            /   \
     *           C    K
     *          / \  / \
     *         A  E I  M
     */
    public void init() {
        symbolTable.put("G", 20);
        symbolTable.put("C", 10);
        symbolTable.put("K", 30);
        symbolTable.put("A", 5);
        symbolTable.put("E", 15);
        symbolTable.put("I", 25);
        symbolTable.put("M", 35);

    }

    @Test
    public void shouldBeNotEmptyAfterPut() {
        assertThat(symbolTable.isEmpty(), is(false));
    }

    @Test
    public void shouldHaveCorrectSizeAfterInsert() {
        assertThat(symbolTable.size(), is(7));
    }

    @Test
    public void shouldHaveCorrectSizeAfterDelete() {
        symbolTable.delete("G");

        assertThat(symbolTable.size(), is(6));
    }

    @Test
    public void shouldUpdateWhenKeyExists() {
        symbolTable.put("G", 1);
        symbolTable.put("K", 2);
        symbolTable.put("E", 3);

        assertThat(symbolTable.get("G"), is(1));
        assertThat(symbolTable.get("K"), is(2));
        assertThat(symbolTable.get("E"), is(3));
        assertThat(symbolTable.size(), is(7));
    }

    @Test
    public void shouldAddWhenKeyMissing() {
        symbolTable.put("B", 1);

        assertThat(symbolTable.get("B"), is(1));
        assertThat(symbolTable.size(), is(8));
    }

    @Test
    public void shouldDelete() {
        symbolTable.delete("C");
        symbolTable.delete("G");
        symbolTable.delete("I");

        assertThat(symbolTable.contains("C"), is(false));
        assertThat(symbolTable.contains("G"), is(false));
        assertThat(symbolTable.contains("I"), is(false));
        assertThat(symbolTable.size(), is(4));
    }

    @Test
    public void shouldReturnKeys() {
        Set<String> expectedKeys = new HashSet<>(Arrays.asList("A", "C", "E", "G", "I", "K", "M"));
        Set<String> actualKeys = StreamSupport.stream(symbolTable.keys().spliterator(), false)
                .collect(Collectors.toSet());

        assertThat(actualKeys, is(expectedKeys));
    }

}