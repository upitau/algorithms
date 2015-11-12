package com.upit.algo.graph;

public class PrimLazyMSTTest extends AbstractMSTTest {

    @Override
    protected MinSpanningTree createMST(EdgeWeightedGraph graph) {
        return new PrimLazyMST(graph);
    }
}