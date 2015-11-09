package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DepthFirstTopologicalOrderTest {
    private DepthFirstTopologicalOrder order;

    @Before
    public void init() {
        Digraph graph = new Digraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 5);
        graph.addEdge(0, 2);
        graph.addEdge(6, 0);
        graph.addEdge(5, 2);
        graph.addEdge(3, 2);
        graph.addEdge(3, 5);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);
        graph.addEdge(6, 4);

        order = new DepthFirstTopologicalOrder(graph);
    }

    @Test
    public void shouldSortInTopologicalOrder() {
        List<Integer> expectedOrder = Arrays.asList(3, 6, 0, 5, 2, 1, 4);
        List<Integer> actualOrder = StreamSupport.stream(order.inTopologicalOrder().spliterator(), false).collect(Collectors.toList());
        assertThat(actualOrder, is(expectedOrder));
    }

}