package com.upit.algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EdgeWeightedGraph {
    private List<Edge>[] adjacencyLists;

    public EdgeWeightedGraph(int numberOfVertices) {
        adjacencyLists = (List<Edge>[]) new List[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyLists[i] = new ArrayList<>();
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        adjacencyLists[v].add(edge);
        int w = edge.other(v);
        adjacencyLists[w].add(edge);
    }

    public Iterable<Edge> adjacentTo(int vertex) {
        return Collections.unmodifiableList(adjacencyLists[vertex]);
    }

    public int numberOfVertices() {
        return adjacencyLists.length;
    }

    public int numberOfEdges() {
        int numberOfEdges = 0;
        for (int v = 0; v < numberOfVertices(); v++) {
            numberOfEdges += adjacencyLists[v].size();
        }
        return numberOfEdges / 2;
    }

}
