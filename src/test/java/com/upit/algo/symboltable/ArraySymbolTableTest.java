package com.upit.algo.symboltable;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArraySymbolTableTest {
    private ArraySymbolTable<String, Integer> symbolTable = new ArraySymbolTable<>();

    @Test
    public void shouldBeEmptyWhenInitialized() {
        assertThat(symbolTable.isEmpty(), is(true));
    }

    @Test
    public void shouldBeNotEmptyAfterPut() {
        symbolTable.put("A", 5);

        assertThat(symbolTable.isEmpty(), is(false));
    }

    @Test
    public void shouldUpdateWhenKeyExists() {
        symbolTable.put("A", 5);
        symbolTable.put("A", 10);

        assertThat(symbolTable.get("A"), is(10));
        assertThat(symbolTable.size(), is(1));
    }

    @Test
    public void shouldAddWhenKeyMissing() {
        symbolTable.put("A", 5);
        symbolTable.put("B", 10);

        assertThat(symbolTable.get("B"), is(10));
        assertThat(symbolTable.size(), is(2));
    }

    @Test
    public void shouldDelete() {
        symbolTable.put("A", 5);
        symbolTable.put("B", 10);
        symbolTable.delete("A");

        assertThat(symbolTable.contains("A"), is(false));
        assertThat(symbolTable.contains("B"), is(true));
        assertThat(symbolTable.size(), is(1));
    }

    @Test
    public void shouldFindMin() {
        symbolTable.put("B", 10);
        symbolTable.put("A", 5);
        symbolTable.put("C", 2);

        assertThat(symbolTable.min(), is("A"));
    }

    @Test
    public void shouldFindMax() {
        symbolTable.put("A", 5);
        symbolTable.put("C", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.max(), is("C"));
    }

    @Test
    public void shouldFindFloorWhenKeyPresent() {
        symbolTable.put("A", 5);
        symbolTable.put("C", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.floor("C"), is("C"));
    }

    @Test
    public void shouldFindFloorWhenKeyMissing() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.floor("G"), is("B"));
    }

    @Test
    public void shouldFindCeilingWhenKeyPresent() {
        symbolTable.put("A", 5);
        symbolTable.put("C", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.ceiling("C"), is("C"));
    }

    @Test
    public void shouldFindCeilingWhenKeyMissing() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.ceiling("G"), is("K"));
    }

    @Test
    public void shouldFindRankWhenKeyPresent() {
        symbolTable.put("A", 5);
        symbolTable.put("C", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.rank("C"), is(2));
    }

    @Test
    public void shouldFindRankWhenKeyMissing() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.rank("G"), is(2));
    }

    @Test
    public void shouldSelect() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("B", 10);

        assertThat(symbolTable.select(1), is("B"));
    }

    @Test
    public void shouldDeleteMin() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("B", 10);
        symbolTable.deleteMin();

        assertThat(symbolTable.min(), is("B"));
    }

    @Test
    public void shouldDeleteMax() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("C", 10);
        symbolTable.deleteMax();

        assertThat(symbolTable.max(), is("C"));
    }

    @Test
    public void shouldReturnRangeSizeWhenKeysExist() {
        symbolTable.put("A", 5);
        symbolTable.put("K", 2);
        symbolTable.put("C", 10);

        assertThat(symbolTable.size("A", "K"), is(3));
    }

    @Test
    public void shouldReturnRangeSizeWhenKeysDoNotExist() {
        symbolTable.put("B", 5);
        symbolTable.put("K", 2);
        symbolTable.put("C", 10);

        assertThat(symbolTable.size("A", "Z"), is(3));
    }

    @Test
    public void shouldReturnKeysInRange() {
        symbolTable.put("B", 5);
        symbolTable.put("K", 2);
        symbolTable.put("C", 10);

        Iterator<String> keys = symbolTable.keys("A", "G").iterator();

        assertThat(keys.hasNext(), is(true));
        assertThat(keys.next(), is("B"));
        assertThat(keys.hasNext(), is(true));
        assertThat(keys.next(), is("C"));
        assertThat(keys.hasNext(), is(false));
    }

    @Test
    public void shouldReturnKeys() {
        symbolTable.put("B", 5);
        symbolTable.put("K", 2);
        symbolTable.put("C", 10);

        Iterator<String> keys = symbolTable.keys().iterator();

        assertThat(keys.hasNext(), is(true));
        assertThat(keys.next(), is("B"));
        assertThat(keys.hasNext(), is(true));
        assertThat(keys.next(), is("C"));
        assertThat(keys.hasNext(), is(true));
        assertThat(keys.next(), is("K"));
        assertThat(keys.hasNext(), is(false));
    }
}