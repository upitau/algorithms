package com.upit.algo.substring;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class KnuthMorrisPrattSubstringSearchTest {

    @Test
    public void shouldFindSubstringWhenExists() {
        KnuthMorrisPrattSubstringSearch substringSearch = new KnuthMorrisPrattSubstringSearch("ABABAC");
        assertThat(substringSearch.indexOf("AABACAABABACAA"), is(6));
    }

    @Test
    public void shouldFindFirstSubstring() {
        KnuthMorrisPrattSubstringSearch substringSearch = new KnuthMorrisPrattSubstringSearch("ABABAC");
        assertThat(substringSearch.indexOf("AABACAABABACAABABAC"), is(6));
    }

    @Test
    public void shouldNotFindSubstringWhenDoesNotExist() {
        KnuthMorrisPrattSubstringSearch substringSearch = new KnuthMorrisPrattSubstringSearch("ABABAD");
        assertThat(substringSearch.indexOf("AABACAABABACAA"), is(-1));
    }

}