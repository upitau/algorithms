package com.upit.algo.graph;

public class BellmanFordShortestPathsTest extends AbstractShortestPathsTest {

    @Override
    protected ShortestPaths createShortestPaths(EdgeWeightedDigraph graph, int source) {
        return new BellmanFordShortestPaths(graph);
    }
}