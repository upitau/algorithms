package com.upit.algo.symboltable;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public abstract class AbstractStringSymbolTableTest {

    protected StringSymbolTable<Integer> symbolTable;

    public AbstractStringSymbolTableTest(StringSymbolTable<Integer> symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Before
    public void init() {
        symbolTable.put("the", 8);
        symbolTable.put("she", 1);
        symbolTable.put("shell", 5);
    }

    @Test
    public void shouldNotContainKeyWhenTrieIsEmpty() {
        symbolTable = new Trie<>();
        assertFalse(symbolTable.contains("key"));
    }

    @Test
    public void shouldGetValueWhenKeyExists() {
        assertThat(symbolTable.get("shell"), is(5));
    }

    @Test
    public void shouldGetNullWhenKeyDoesNotExist() {
        assertNull(symbolTable.get("sh"));
        assertNull(symbolTable.get("some"));
    }

    @Test
    public void shouldContainKeyWhenKeyExists() {
        assertTrue(symbolTable.contains("she"));
        assertTrue(symbolTable.contains("shell"));
    }

    @Test
    public void shouldNotContainKeyWhenKeyDoesNotExist() {
        assertFalse(symbolTable.contains("sh"));
        assertFalse(symbolTable.contains("some"));
    }

    @Test
    public void shouldPutLongestKey() {
        symbolTable.put("shells", 10);
        assertTrue(symbolTable.contains("shells"));
        assertThat(symbolTable.get("shells"), is(10));

        assertTrue(symbolTable.contains("she"));
    }

    @Test
    public void shouldPutSubKey() {
        symbolTable.put("sh", 11);
        assertTrue(symbolTable.contains("sh"));
        assertThat(symbolTable.get("sh"), is(11));

        assertTrue(symbolTable.contains("she"));
    }

    @Test
    public void shouldDeleteLongestKey() {
        symbolTable.delete("shell");
        assertFalse(symbolTable.contains("shell"));
        symbolTable.delete("shell");
        assertFalse(symbolTable.contains("shell"));

        assertTrue(symbolTable.contains("she"));
    }

    @Test
    public void shouldDeleteSubkey() {
        symbolTable.delete("she");
        assertFalse(symbolTable.contains("she"));
        symbolTable.delete("she");
        assertFalse(symbolTable.contains("she"));

        assertTrue(symbolTable.contains("shell"));
    }

    @Test
    public void shouldDeleteKeyWhenKeyDoesNotExist() {
        symbolTable.delete("some");
        assertFalse(symbolTable.contains("some"));
    }

    @Test
    public void shouldReturnKeys() {
        List<String> expectedKeys = Arrays.asList("she", "shell", "the");
        Iterable<String> actualKeys = StreamSupport.stream(symbolTable.keys().spliterator(), false)
                .collect(Collectors.toList());
        assertThat(actualKeys, is(expectedKeys));
    }

    @Test
    public void shouldFindKeysWithPrefixWhenExist() {
        symbolTable.put("sh", 4);
        List<String> expectedKeys = Arrays.asList("she", "shell");
        List<String> actualKeys = StreamSupport.stream(symbolTable.keysWithPrefix("she").spliterator(), false)
                .collect(Collectors.toList());
        assertThat(actualKeys, is(expectedKeys));
    }

    @Test
    public void shouldNotFindKeysWithPrefixWhenDoesNotExist() {
        List<String> actualKeys = StreamSupport.stream(symbolTable.keysWithPrefix("a").spliterator(), false)
                .collect(Collectors.toList());
        assertTrue(actualKeys.isEmpty());
    }

    @Test
    public void shouldFindLongestPrefixWhenMatchesOneOfKeys() {
        symbolTable.put("shells", 4);
        assertThat(symbolTable.longestPrefixOf("shell"), is("shell"));
    }

    @Test
    public void shouldFindLongestPrefixWhenDoesNotMatchesOneOfKeys() {
        assertThat(symbolTable.longestPrefixOf("shel"), is("she"));
    }

    @Test
    public void shouldNotFindLongestPrefixWhenDoesNotExist() {
        assertThat(symbolTable.longestPrefixOf("sh"), is(""));
    }

}