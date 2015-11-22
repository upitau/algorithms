package com.upit.algo.other;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LongestRepeatedSubstringTest {

    @Test
    public void shouldFindLongestRepeatedSubstring() {
        String result = new LongestRepeatedSubstring().find("aacaagtttacaagc");
        assertThat(result, is("acaag"));
    }

}