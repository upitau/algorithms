package com.upit.algo.graph;

public class DijkstraShortestPathsTest extends AbstractShortestPathsTest {
    @Override
    protected DirectedTraversable createShortestPaths(EdgeWeightedDigraph graph, int source) {
        return new DijkstraShortestPaths(graph, source);
    }
}