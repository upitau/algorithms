package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KruskalMinimumSpanningTreeTest {
    private MinimumSpanningTree mst;

    @Before
    public void init() {
        EdgeWeightedGraph graph = new EdgeWeightedGraph(8);
        graph.addEdge(new Edge(0, 2, 0.26));
        graph.addEdge(new Edge(0, 4, 0.38));
        graph.addEdge(new Edge(0, 7, 0.16));
        graph.addEdge(new Edge(1, 2, 0.36));
        graph.addEdge(new Edge(1, 3, 0.29));
        graph.addEdge(new Edge(1, 5, 0.32));
        graph.addEdge(new Edge(1, 7, 0.19));
        graph.addEdge(new Edge(2, 3, 0.17));
        graph.addEdge(new Edge(2, 7, 0.34));
        graph.addEdge(new Edge(3, 6, 0.52));
        graph.addEdge(new Edge(4, 5, 0.35));
        graph.addEdge(new Edge(4, 7, 0.37));
        graph.addEdge(new Edge(5, 7, 0.28));
        graph.addEdge(new Edge(6, 0, 0.58));
        graph.addEdge(new Edge(6, 2, 0.40));
        graph.addEdge(new Edge(6, 4, 0.93));

        mst = new KruskalMinimumSpanningTree(graph);
    }

    @Test
    public void shouldFindMST() {
        Set<Edge> expected = new HashSet<>();
        expected.add(new Edge(4, 5, 0.35));
        expected.add(new Edge(5, 7, 0.28));
        expected.add(new Edge(1, 7, 0.19));
        expected.add(new Edge(0, 7, 0.16));
        expected.add(new Edge(0, 2, 0.26));
        expected.add(new Edge(2, 3, 0.17));
        expected.add(new Edge(6, 2, 0.40));

        Set<Edge> actual = StreamSupport.stream(mst.edges().spliterator(), false).collect(Collectors.toSet());
        assertThat(actual, is(expected));
    }

}