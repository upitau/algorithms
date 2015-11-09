package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class KosarajuSharirStronglyConnectedComponentsTest {
    KosarajuSharirStronglyConnectedComponents connectedComponents;

    @Before
    public void init() {
        Digraph graph = new Digraph(13);
        graph.addEdge(0, 5);
        graph.addEdge(0, 1);
        graph.addEdge(2, 0);
        graph.addEdge(6, 0);
        graph.addEdge(6, 8);
        graph.addEdge(8, 6);
        graph.addEdge(7, 6);
        graph.addEdge(3, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 2);
        graph.addEdge(6, 4);
        graph.addEdge(6, 9);
        graph.addEdge(7, 9);
        graph.addEdge(3, 5);
        graph.addEdge(5, 4);
        graph.addEdge(4, 3);
        graph.addEdge(11, 4);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(10, 12);
        graph.addEdge(11, 12);
        graph.addEdge(12, 9);

        connectedComponents = new KosarajuSharirStronglyConnectedComponents(graph);
    }

    @Test
    public void shouldFindCorrectNumberOfConnectedComponents() {
        assertThat(connectedComponents.getCount(), is(5));
    }

    @Test
    public void shouldPlaceNodesToTheSameComponentWhenTheyAreConnected() {
        assertTrue(connectedComponents.getId(0) == connectedComponents.getId(2));
    }

    @Test
    public void shouldPlaceNodesToDifferentComponentWhenTheyAreDisconnectedConnected() {
        assertFalse(connectedComponents.getId(0) == connectedComponents.getId(8));
    }

}