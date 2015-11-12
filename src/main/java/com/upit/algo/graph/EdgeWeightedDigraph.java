package com.upit.algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EdgeWeightedDigraph {
    private List<DirectedEdge>[] adjacencyLists;

    public EdgeWeightedDigraph(int numberOfVertices) {
        adjacencyLists = (List<DirectedEdge>[]) new List[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            adjacencyLists[i] = new ArrayList<>();
        }
    }

    public void addEdge(DirectedEdge edge) {
        adjacencyLists[edge.from()].add(edge);
    }

    public Iterable<DirectedEdge> adjacentTo(int vertex) {
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
        return numberOfEdges;
    }

    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> edges = new ArrayList<>();
        for (int vertex = 0; vertex < numberOfVertices(); vertex++) {
            for (DirectedEdge edge: adjacentTo(vertex)) {
                edges.add(edge);
            }
        }
        return edges;
    }

}
