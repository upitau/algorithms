package com.upit.algo.substring;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BoyerMooreSubstringSearchTest {

    @Test
    public void shouldFindSubstringWhenExists() {
        BoyerMooreSubstringSearch substringSearch = new BoyerMooreSubstringSearch("ABABAC");
        assertThat(substringSearch.indexOf("AABACAABABACAA"), is(6));
    }

    @Test
    public void shouldFindFirstSubstring() {
        BoyerMooreSubstringSearch substringSearch = new BoyerMooreSubstringSearch("ABABAC");
        assertThat(substringSearch.indexOf("AABACAABABACAABABAC"), is(6));
    }

    @Test
    public void shouldNotFindSubstringWhenDoesNotExist() {
        BoyerMooreSubstringSearch substringSearch = new BoyerMooreSubstringSearch("ABABAD");
        assertThat(substringSearch.indexOf("AABACAABABACAA"), is(-1));
    }

}