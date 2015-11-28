package com.upit.algo.symboltable;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TrieTest {

    private Trie<Integer> trie = new Trie<>();

    @Before
    public void init() {
        trie.put("she", 1);
        trie.put("shell", 5);
    }

    @Test
    public void shouldNotContainKeyWhenTrieIsEmpty() {
        trie = new Trie<>();
        assertFalse(trie.contains("key"));
    }

    @Test
    public void shouldGetValueWhenKeyExists() {
        assertThat(trie.get("shell"), is(5));
    }

    @Test
    public void shouldGetNullWhenKeyDoesNotExist() {
        assertNull(trie.get("sh"));
        assertNull(trie.get("some"));
    }

    @Test
    public void shouldContainKeyWhenKeyExists() {
        assertTrue(trie.contains("she"));
        assertTrue(trie.contains("shell"));
    }

    @Test
    public void shouldNotContainKeyWhenKeyDoesNotExist() {
        assertFalse(trie.contains("sh"));
        assertFalse(trie.contains("some"));
    }

    @Test
    public void shouldDeleteLongestKey() {
        trie.delete("shell");
        assertFalse(trie.contains("shell"));
        trie.delete("shell");
        assertFalse(trie.contains("shell"));

        assertTrue(trie.contains("she"));
    }

    @Test
    public void shouldDeleteSubkey() {
        trie.delete("she");
        assertFalse(trie.contains("she"));
        trie.delete("she");
        assertFalse(trie.contains("she"));

        assertTrue(trie.contains("shell"));
    }

    @Test
    public void shouldDeleteKeyWhenKeyDoesNotExist() {
        trie.delete("some");
        assertFalse(trie.contains("some"));
    }

}