package com.upit.algo.graph;

public class DirectedGraph extends AbstractGraph {

    public DirectedGraph(int numberOfVertices) {
        super(numberOfVertices);
    }

    @Override
    public void addEdge(int vertexFrom, int vertexTo) {
        adjacencyLists[vertexFrom].add(vertexTo);
    }

    @Override
    public int numberOfEdges() {
        int numberOfEdges = 0;
        for (int v = 0; v < numberOfVertices(); v++) {
            numberOfEdges += adjacencyLists[v].size();
        }
        return numberOfEdges;
    }

    public DirectedGraph reverse() {
        DirectedGraph reverseGraph = new DirectedGraph(numberOfVertices());
        for (int vertex = 0; vertex < numberOfVertices(); vertex++) {
            for (int adjacentVertex: adjacentTo(vertex)) {
                reverseGraph.addEdge(adjacentVertex, vertex);
            }
        }
        return reverseGraph;
    }
}
