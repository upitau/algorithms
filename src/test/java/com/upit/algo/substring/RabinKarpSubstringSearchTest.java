package com.upit.algo.substring;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RabinKarpSubstringSearchTest {

    @Test
    public void shouldFindSubstringWhenExists() {
        RabinKarpSubstringSearch substringSearch = new RabinKarpSubstringSearch("ABABAC");
        assertThat(substringSearch.indexOf("AABACAABABACAA"), is(6));
    }

    @Test
    public void shouldFindFirstSubstring() {
        RabinKarpSubstringSearch substringSearch = new RabinKarpSubstringSearch("ABABAC");
        assertThat(substringSearch.indexOf("AABACAABABACAABABAC"), is(6));
    }

    @Test
    public void shouldNotFindSubstringWhenDoesNotExist() {
        RabinKarpSubstringSearch substringSearch = new RabinKarpSubstringSearch("ABABAD");
        assertThat(substringSearch.indexOf("AABACAABABACAA"), is(-1));
    }

}