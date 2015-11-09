package com.upit.algo.graph;

public interface Graph {
    void addEdge(int sourceVertex, int targetVertex);

    Iterable<Integer> adjacentTo(int vertex);

    int numberOfVertices();

    int numberOfEdges();
}
