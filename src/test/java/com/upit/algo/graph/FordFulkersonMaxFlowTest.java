package com.upit.algo.graph;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FordFulkersonMaxFlowTest {
    private FordFulkersonMaxFlow maxFlow;

    @Before
    public void init() {
        FlowNetwork network = new FlowNetwork(8);
        network.addEdge(new FlowEdge(0, 1, 9));
        network.addEdge(new FlowEdge(0, 3, 4));
        network.addEdge(new FlowEdge(0, 4, 15));
        network.addEdge(new FlowEdge(1, 4, 15));
        network.addEdge(new FlowEdge(1, 5, 10));
        network.addEdge(new FlowEdge(2, 0, 10));
        network.addEdge(new FlowEdge(2, 3, 5));
        network.addEdge(new FlowEdge(2, 6, 15));
        network.addEdge(new FlowEdge(3, 4, 8));
        network.addEdge(new FlowEdge(3, 6, 4));
        network.addEdge(new FlowEdge(4, 5, 10));
        network.addEdge(new FlowEdge(4, 7, 15));
        network.addEdge(new FlowEdge(6, 7, 16));
        network.addEdge(new FlowEdge(7, 3, 6));
        network.addEdge(new FlowEdge(7, 5, 10));

        maxFlow = new FordFulkersonMaxFlow(network, 2, 5);
    }

    @Test
    public void shouldFindMaxFlow() {
        assertThat(maxFlow.value(), is(28.0));
    }

    @Test
    public void shouldFindMinCut() {
        assertThat(maxFlow.inCut(0), is(false));
        assertThat(maxFlow.inCut(1), is(false));
        assertThat(maxFlow.inCut(2), is(true));
        assertThat(maxFlow.inCut(3), is(true));
        assertThat(maxFlow.inCut(4), is(false));
        assertThat(maxFlow.inCut(5), is(false));
        assertThat(maxFlow.inCut(6), is(true));
        assertThat(maxFlow.inCut(7), is(true));
    }

}