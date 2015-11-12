package com.upit.algo.graph;

public class PrimMSTTest extends AbstractMSTTest {

    @Override
    protected MinSpanningTree createMST(EdgeWeightedGraph graph) {
        return new PrimMST(graph);
    }
}