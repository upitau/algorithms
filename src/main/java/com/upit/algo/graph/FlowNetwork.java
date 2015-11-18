package com.upit.algo.graph;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {

    private List<FlowEdge>[] adjacent;

    public FlowNetwork(int numberOfVerteces) {
        adjacent = (List<FlowEdge>[]) new List[numberOfVerteces];
        for (int vertex = 0; vertex < numberOfVerteces; vertex++) {
            adjacent[vertex] = new ArrayList<>();
        }
    }

    public int numberOfVertices() {
        return adjacent.length;
    }

    public void addEdge(FlowEdge edge) {
        int from = edge.from();
        int to = edge.to();
        adjacent[from].add(edge);
        adjacent[to].add(edge);
    }

    public Iterable<FlowEdge> adjacentTo(int vertex) {
        return adjacent[vertex];
    }
}
