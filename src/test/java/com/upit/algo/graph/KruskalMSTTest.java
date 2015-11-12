package com.upit.algo.graph;

public class KruskalMSTTest extends AbstractMSTTest {

    @Override
    protected MinSpanningTree createMST(EdgeWeightedGraph graph) {
        return new KruskalMST(graph);
    }
}