package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractShortestPathsTest {
    private DirectedTraversable directedTraversable;

    @Before
    public void init() {
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(8);
        graph.addEdge(new DirectedEdge(0, 4, 9));
        graph.addEdge(new DirectedEdge(0, 1, 5));
        graph.addEdge(new DirectedEdge(0, 7, 8));
        graph.addEdge(new DirectedEdge(4, 7, 5));
        graph.addEdge(new DirectedEdge(4, 5, 4));
        graph.addEdge(new DirectedEdge(4, 6, 20));
        graph.addEdge(new DirectedEdge(1, 7, 4));
        graph.addEdge(new DirectedEdge(1, 3, 15));
        graph.addEdge(new DirectedEdge(1, 2, 12));
        graph.addEdge(new DirectedEdge(7, 2, 7));
        graph.addEdge(new DirectedEdge(7, 5, 6));
        graph.addEdge(new DirectedEdge(5, 2, 1));
        graph.addEdge(new DirectedEdge(5, 6, 13));
        graph.addEdge(new DirectedEdge(2, 3, 3));
        graph.addEdge(new DirectedEdge(2, 6, 11));
        graph.addEdge(new DirectedEdge(3, 6, 9));

        directedTraversable = createShortestPaths(graph, 0);
    }

    abstract protected DirectedTraversable createShortestPaths(EdgeWeightedDigraph graph, int source);

    @Test
    public void shouldHavePathsToAllVerteces() {
        for (int vertex = 0; vertex < 8; vertex++) {
            assertThat(directedTraversable.hasPathTo(vertex), is(true));
        }
    }

    @Test
    public void shouldFindShortestDistances() {
        double[] expectedDistances = {0.0, 5.0, 14.0, 17.0, 9.0, 13.0, 25.0, 8.0};
        for (int vertex = 0; vertex < 8; vertex++) {
            assertThat(directedTraversable.distanceTo(vertex), is(expectedDistances[vertex]));
        }
    }

    @Test
    public void shouldFindCorrectPathToMostDistantVertex() {
        List<DirectedEdge> expectedPath = Arrays.asList(new DirectedEdge(0, 4, 9), new DirectedEdge(4, 5, 4), new DirectedEdge(5, 2, 1), new DirectedEdge(2, 6, 11));
        List<DirectedEdge> actualPath = StreamSupport.stream(directedTraversable.pathTo(6).spliterator(), false)
                .collect(Collectors.toList());
        assertThat(actualPath, is(expectedPath));

    }

}