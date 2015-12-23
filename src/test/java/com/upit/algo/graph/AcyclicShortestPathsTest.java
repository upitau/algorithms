package com.upit.algo.graph;

public class AcyclicShortestPathsTest extends AbstractShortestPathsTest {

    @Override
    protected DirectedTraversable createShortestPaths(EdgeWeightedDigraph graph, int source) {
        return new AcyclicShortestPaths(graph, source);
    }

}