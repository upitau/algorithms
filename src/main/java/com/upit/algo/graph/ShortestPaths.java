package com.upit.algo.graph;

public interface ShortestPaths {

    boolean hasPathTo(int vertex);

    double distanceTo(int vertex);

    Iterable<DirectedEdge> pathTo(int target);
}
