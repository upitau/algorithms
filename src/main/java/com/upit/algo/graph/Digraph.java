package com.upit.algo.graph;

public class Digraph extends AbstractGraph {

    public Digraph(int numberOfVertices) {
        super(numberOfVertices);
    }

    public void addEdge(int vertexFrom, int vertexTo) {
        adjacencyLists[vertexFrom].add(vertexTo);
    }

    public int numberOfEdges() {
        int numberOfEdges = 0;
        for (int v = 0; v < numberOfVertices(); v++) {
            numberOfEdges += adjacencyLists[v].size();
        }
        return numberOfEdges;
    }

    public Digraph reverse() {
        Digraph reverseGraph = new Digraph(numberOfVertices());
        for (int vertex = 0; vertex < numberOfVertices(); vertex++) {
            for (int adjacentVertex: adjacentTo(vertex)) {
                reverseGraph.addEdge(adjacentVertex, vertex);
            }
        }
        return reverseGraph;
    }
}
