package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConnectedComponentsTest {
    ConnectedComponents connectedComponents;

    @Before
    public void init() {
        UndirectedGraph graph = new UndirectedGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);

        connectedComponents = new ConnectedComponents(graph);
    }

    @Test
    public void shouldFindCorrectNumberOfConnectedComponents() {
        assertThat(connectedComponents.getCount(), is(3));
    }

    @Test
    public void shouldPlaceNodesToTheSameComponentWhenTheyAreConnected() {
        assertTrue(connectedComponents.getId(0) == connectedComponents.getId(2));
    }

    @Test
    public void shouldPlaceNodesToDifferentComponentWhenTheyAreDisconnectedConnected() {
        assertFalse(connectedComponents.getId(0) == connectedComponents.getId(3));
    }

}