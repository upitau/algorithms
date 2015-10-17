package com.upit.algo.symboltable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public abstract class AbstractOrderedSymbolTableTest {
    protected OrderedSymbolTable<String, Integer> symbolTable;

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
    public void shouldHaveCorrectSizeAfterDeleteMin() {
        symbolTable.deleteMin();

        assertThat(symbolTable.size(), is(6));
    }

    @Test
    public void shouldHaveCorrectSizeAfterDeleteMax() {
        symbolTable.deleteMax();

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
    public void shouldFindMin() {
        assertThat(symbolTable.min(), is("A"));
    }

    @Test
    public void shouldFindMax() {
        assertThat(symbolTable.max(), is("M"));
    }

    @Test
    public void shouldFindFloorWhenKeyPresent() {
        assertThat(symbolTable.floor("G"), is("G"));
        assertThat(symbolTable.floor("K"), is("K"));
        assertThat(symbolTable.floor("E"), is("E"));
    }

    @Test
    public void shouldFindFloorWhenKeyMissing() {
        assertThat(symbolTable.floor("B"), is("A"));
        assertThat(symbolTable.floor("D"), is("C"));
        assertThat(symbolTable.floor("F"), is("E"));
        assertThat(symbolTable.floor("H"), is("G"));
        assertThat(symbolTable.floor("J"), is("I"));
        assertThat(symbolTable.floor("L"), is("K"));
        assertThat(symbolTable.floor("N"), is("M"));
    }

    @Test
    public void shouldFindCeilingWhenKeyPresent() {
        assertThat(symbolTable.floor("G"), is("G"));
        assertThat(symbolTable.floor("K"), is("K"));
        assertThat(symbolTable.floor("E"), is("E"));
    }

    @Test
    public void shouldFindCeilingWhenKeyMissing() {
        assertThat(symbolTable.ceiling("B"), is("C"));
        assertThat(symbolTable.ceiling("D"), is("E"));
        assertThat(symbolTable.ceiling("F"), is("G"));
        assertThat(symbolTable.ceiling("H"), is("I"));
        assertThat(symbolTable.ceiling("J"), is("K"));
        assertThat(symbolTable.ceiling("L"), is("M"));
        assertThat(symbolTable.ceiling("N"), nullValue());
    }

    @Test
    public void shouldFindRankWhenKeyPresent() {
        assertThat(symbolTable.rank("C"), is(1));
        assertThat(symbolTable.rank("G"), is(3));
        assertThat(symbolTable.rank("I"), is(4));
    }

    @Test
    public void shouldFindRankWhenKeyMissing() {
        assertThat(symbolTable.rank("D"), is(2));
        assertThat(symbolTable.rank("H"), is(4));
        assertThat(symbolTable.rank("J"), is(5));
    }

    @Test
    public void shouldSelect() {
        assertThat(symbolTable.select(0), is("A"));
        assertThat(symbolTable.select(3), is("G"));
        assertThat(symbolTable.select(5), is("K"));
    }

    @Test
    public void shouldDeleteMin() {
        symbolTable.deleteMin();

        assertThat(symbolTable.min(), is("C"));
    }

    @Test
    public void shouldDeleteMax() {
        symbolTable.deleteMax();

        assertThat(symbolTable.max(), is("K"));
    }

    @Test
    public void shouldReturnRangeSizeWhenKeysExist() {
        assertThat(symbolTable.size("C", "K"), is(5));
    }

    @Test
    public void shouldReturnRangeSizeWhenKeysDoNotExist() {
        assertThat(symbolTable.size("B", "L"), is(5));
    }

    @Test
    public void shouldReturnKeysInRangeWhenKeysExist() {
        List<String> expectedKeys = Arrays.asList("C", "E", "G", "I", "K");
        List<String> actualKeys = StreamSupport.stream(symbolTable.keys("C", "K").spliterator(), false)
                .collect(Collectors.toList());

        assertThat(actualKeys, is(expectedKeys));
    }

    @Test
    public void shouldReturnKeysInRangeWhenKeysDoNotExist() {
        List<String> expectedKeys = Arrays.asList("C", "E", "G", "I", "K");
        List<String> actualKeys = StreamSupport.stream(symbolTable.keys("B", "L").spliterator(), false)
                .collect(Collectors.toList());

        assertThat(actualKeys, is(expectedKeys));
    }

    @Test
    public void shouldReturnOrderedKeys() {
        List<String> expectedKeys = Arrays.asList("A", "C", "E", "G", "I", "K", "M");
        List<String> actualKeys = StreamSupport.stream(symbolTable.keys().spliterator(), false)
                .collect(Collectors.toList());

        assertThat(actualKeys, is(expectedKeys));
    }

}