package com.upit.algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractGraph {
    protected List<Integer>[] adjacencyLists;

    public AbstractGraph(int numberOfVertices) {
        adjacencyLists = (List<Integer>[]) new List[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyLists[i] = new ArrayList<>();
        }
    }

    public Iterable<Integer> adjacentTo(int vertex) {
        return Collections.unmodifiableList(adjacencyLists[vertex]);
    }

    public int numberOfVertices() {
        return adjacencyLists.length;
    }
}
