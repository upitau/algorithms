package com.upit.algo.graph;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EdgeWeightedGraphTest {

    private EdgeWeightedGraph graph = new EdgeWeightedGraph(5);

    @Test
    public void shouldCreateGraphWithoutEdges() {
        assertThat(graph.numberOfVertices(), is(5));
        assertThat(graph.numberOfEdges(), is(0));
    }

    @Test
    public void shouldAddEdge() {
        graph.addEdge(new Edge(1, 2, 10));
        assertThat(toList(graph.adjacentTo(1)).get(0).other(1), is(2));
        assertThat(toList(graph.adjacentTo(1)).get(0).weight(), is(10D));
        assertThat(toList(graph.adjacentTo(2)).get(0).other(2), is(1));
        assertThat(toList(graph.adjacentTo(2)).get(0).weight(), is(10D));
    }

    @Test
    public void shouldReturnNoAdjacentVerticesForVertexWithZeroDegree() {
        assertThat(toList(graph.adjacentTo(1)).size(), is(0));
    }

    @Test
    public void shouldReturnAdjacentVerticesForVertexWithNonZeroDegree() {
        graph.addEdge(new Edge(1, 2, 10));
        List<Edge> adjacentVerteces = toList(graph.adjacentTo(1));
        assertThat(adjacentVerteces.size(), is(1));
        assertThat(adjacentVerteces.get(0).other(1), is(2));
    }

    @Test
    public void shouldReturnNumberOfVertices() {
        graph.addEdge(new Edge(1, 2, 10));
        assertThat(graph.numberOfVertices(), is(5));
    }

    @Test
    public void shouldReturnNumberOfEdges() {
        graph.addEdge(new Edge(1, 2, 10));
        assertThat(graph.numberOfEdges(), is(1));
    }

    private List<Edge> toList(Iterable<Edge> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}