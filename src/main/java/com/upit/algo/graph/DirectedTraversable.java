package com.upit.algo.graph;

public interface DirectedTraversable {

    boolean hasPathTo(int vertex);

    double distanceTo(int vertex);

    Iterable<DirectedEdge> pathTo(int target);
}
