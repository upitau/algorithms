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
        for (int vertex = 0; vertex < numberOfVertices(); vertex++) {
            numberOfEdges += adjacencyLists[vertex].size();
        }
        return numberOfEdges / 2;
    }

    public Iterable<Edge> edges() {
        List<Edge> edges = new ArrayList<>();
        for (int vertex = 0; vertex < numberOfVertices(); vertex++) {
            for (Edge edge: adjacentTo(vertex)) {
                if (edge.other(vertex) > vertex) {
                    edges.add(edge);
                }
            }
        }
        return edges;
    }

}
