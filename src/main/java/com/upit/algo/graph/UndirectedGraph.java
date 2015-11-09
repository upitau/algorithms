package com.upit.algo.graph;

public class UndirectedGraph extends AbstractGraph {

    public UndirectedGraph(int numberOfVertices) {
        super(numberOfVertices);
    }

    @Override
    public void addEdge(int sourceVertex, int targetVertex) {
        adjacencyLists[sourceVertex].add(targetVertex);
        adjacencyLists[targetVertex].add(sourceVertex);
    }

    @Override
    public int numberOfEdges() {
        int numberOfEdges = 0;
        for (int v = 0; v < numberOfVertices(); v++) {
            numberOfEdges += degree(v);
        }
        return numberOfEdges / 2;
    }

    public int degree(int vertex) {
        int degree = 0;
        for (Integer v: adjacentTo(vertex)) {
            degree++;
        }
        return degree;
    }

    public int maxDegree() {
        int maxDegree = 0;
        for (int v = 0; v < numberOfVertices(); v++) {
            maxDegree = Math.max(maxDegree, degree(v));
        }
        return maxDegree;
    }

    public double averageDegree() {
        return 2.0 * numberOfEdges() / numberOfVertices();
    }

    public int numberOfSelfLoops() {
        int numberOfSelfLoops = 0;
        for (int v = 0; v < numberOfVertices(); v++) {
            for (Integer w: adjacentTo(v)) {
                if (v == w) {
                    numberOfSelfLoops++;
                }
            }
        }
        return numberOfSelfLoops / 2;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
