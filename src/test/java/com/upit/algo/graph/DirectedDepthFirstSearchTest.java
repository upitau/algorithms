package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectedDepthFirstSearchTest {
    private Digraph digraph;

    @Before
    public void init() {
        digraph = new Digraph(6);
        digraph.addEdge(0, 1);
        digraph.addEdge(1, 2);
        digraph.addEdge(2, 0);
        digraph.addEdge(3, 4);
    }

    @Test
    public void shouldFindPathFromSingleSourceWhenExists() {
        DirectedDepthFirstSearch dfs = new DirectedDepthFirstSearch(digraph, 0);
        assertTrue(dfs.hasPathTo(0));
        assertTrue(dfs.hasPathTo(1));
        assertTrue(dfs.hasPathTo(2));
    }

    @Test
    public void shouldNotFindPathFromSingleSourceWhenDoesNotExist() {
        DirectedDepthFirstSearch dfs = new DirectedDepthFirstSearch(digraph, 0);
        assertFalse(dfs.hasPathTo(3));
        assertFalse(dfs.hasPathTo(4));
        assertFalse(dfs.hasPathTo(5));
    }

    @Test
    public void shouldFindPathFromMultipleSourceWhenExists() {
        DirectedDepthFirstSearch dfs = new DirectedDepthFirstSearch(digraph, Arrays.asList(1, 3));
        assertTrue(dfs.hasPathTo(0));
        assertTrue(dfs.hasPathTo(1));
        assertTrue(dfs.hasPathTo(2));
        assertTrue(dfs.hasPathTo(3));
        assertTrue(dfs.hasPathTo(4));
    }

    @Test
    public void shouldNotFindPathFromMultipleSourceWhenDoesNotExist() {
        DirectedDepthFirstSearch dfs = new DirectedDepthFirstSearch(digraph, Arrays.asList(1, 3));
        assertFalse(dfs.hasPathTo(5));
    }

}