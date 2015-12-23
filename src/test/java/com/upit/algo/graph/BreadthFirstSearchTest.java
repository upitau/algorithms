package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BreadthFirstSearchTest {
    private Traversable traversable;

    @Before
    public void init() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        traversable = new BreadthFirstSearch(graph, 0);
    }

    @Test
    public void shouldHavePathToConnectedVertex() {
        assertThat(traversable.hasPathTo(2), is(true));

        List<Integer> path = StreamSupport.stream(traversable.pathTo(2).spliterator(), false).collect(Collectors.toList());
        assertThat(path, is(Arrays.asList(0, 1, 2)));
    }

    @Test
    public void shouldNotHavePathToNonConnectedVertex() {
        assertThat(traversable.hasPathTo(3), is(false));
        assertNull(traversable.pathTo(3));
    }

}