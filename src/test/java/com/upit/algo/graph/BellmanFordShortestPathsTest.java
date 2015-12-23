package com.upit.algo.graph;

public class BellmanFordShortestPathsTest extends AbstractShortestPathsTest {

    @Override
    protected DirectedTraversable createShortestPaths(EdgeWeightedDigraph graph, int source) {
        return new BellmanFordShortestPaths(graph);
    }
}