package com.upit.algo.graph;

public interface MinSpanningTree {

    Iterable<Edge> edges();

    default double weight() {
        double weight = 0;
        for (Edge edge: edges()) {
            weight += edge.weight();
        }
        return weight;
    }
}
