package com.upit.algo.graph;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DirectedGraphTest {

    private DirectedGraph graph = new DirectedGraph(5);

    @Test
    public void shouldCreateGraphWithoutEdges() {
        assertThat(graph.numberOfVertices(), is(5));
        assertThat(graph.numberOfEdges(), is(0));
    }

    @Test
    public void shouldAddEdge() {
        graph.addEdge(1, 2);
        assertThat(StreamSupport.stream(graph.adjacentTo(1).spliterator(), false).anyMatch(new Integer(2)::equals), is(true));
        assertThat(StreamSupport.stream(graph.adjacentTo(2).spliterator(), false).anyMatch(new Integer(1)::equals), is(false));
    }

    @Test
    public void shouldReturnNoAdjacentVerticesForVertexWithZeroDegree() {
        assertThat(StreamSupport.stream(graph.adjacentTo(1).spliterator(), false).count(), is(0L));
    }

    @Test
    public void shouldReturnAdjacentVerticesForVertexWithNonZeroDegree() {
        graph.addEdge(1, 2);
        List<Integer> adjacentVerteces = StreamSupport.stream(graph.adjacentTo(1).spliterator(), false)
                .collect(Collectors.toList());
        assertThat(adjacentVerteces.size(), is(1));
        assertThat(adjacentVerteces.get(0), is(2));
    }

    @Test
    public void shouldReturnNumberOfVertices() {
        graph.addEdge(1, 2);
        assertThat(graph.numberOfVertices(), is(5));
    }

    @Test
    public void shouldReturnNumberOfEdges() {
        graph.addEdge(1, 2);
        assertThat(graph.numberOfEdges(), is(1));
    }

}