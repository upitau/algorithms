package com.upit.algo.re;

import org.junit.Test;

import static org.junit.Assert.*;

public class NFATest {

    @Test
    public void shouldFindExactMatch() {
        NFA nfa = new NFA("(abc)");
        assertTrue(nfa.recognizes("abc"));

        assertFalse(nfa.recognizes("a"));
        assertFalse(nfa.recognizes("ab"));
        assertFalse(nfa.recognizes("cd"));
        assertFalse(nfa.recognizes("abcd"));
    }

    @Test
    public void shouldFindOrMatch() {
        NFA nfa = new NFA("(abc|def|ghi)");
        assertTrue(nfa.recognizes("abc"));
        assertTrue(nfa.recognizes("def"));
        assertTrue(nfa.recognizes("ghi"));

        assertFalse(nfa.recognizes("ab"));
        assertFalse(nfa.recognizes("abcd"));
        assertFalse(nfa.recognizes("ef"));
        assertFalse(nfa.recognizes("adef"));
        assertFalse(nfa.recognizes("fghi"));
    }

    @Test
    public void shouldFindClosureMatch() {
        NFA nfa = new NFA("(a(bc*)*d.*)");
        assertTrue(nfa.recognizes("ad"));
        assertTrue(nfa.recognizes("abcd"));
        assertTrue(nfa.recognizes("abd"));
        assertTrue(nfa.recognizes("abccbd"));
        assertTrue(nfa.recognizes("abccbccd"));
        assertTrue(nfa.recognizes("abcde"));
        assertTrue(nfa.recognizes("abcdefgh"));

        assertFalse(nfa.recognizes("a"));
        assertFalse(nfa.recognizes("ab"));
        assertFalse(nfa.recognizes("abc"));
        assertFalse(nfa.recognizes("acd"));
        assertFalse(nfa.recognizes("abcee"));
    }

    @Test
    public void shouldFindOrWithClosureMatch() {
        NFA nfa = new NFA("(a(bc*|e.*)*d)");
        assertTrue(nfa.recognizes("ad"));
        assertTrue(nfa.recognizes("abcd"));
        assertTrue(nfa.recognizes("abd"));
        assertTrue(nfa.recognizes("abccbd"));
        assertTrue(nfa.recognizes("abccbccd"));
        assertTrue(nfa.recognizes("aed"));
        assertTrue(nfa.recognizes("aeaad"));
        assertTrue(nfa.recognizes("abccbcceaded"));

        assertFalse(nfa.recognizes("a"));
        assertFalse(nfa.recognizes("ab"));
        assertFalse(nfa.recognizes("abc"));
        assertFalse(nfa.recognizes("acd"));
        assertFalse(nfa.recognizes("abcdd"));
        assertFalse(nfa.recognizes("aded"));
    }

}