package com.upit.algo.graph;

import com.upit.algo.queue.IndexMinPriorityQueue;
import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

import java.util.Arrays;

public class DijkstraShortestPaths implements DirectedTraversable {
    private DirectedEdge[] edgeTo;
    private double[] distanceTo;
    private IndexMinPriorityQueue<Double> pq;

    public DijkstraShortestPaths(EdgeWeightedDigraph graph, int source) {
        edgeTo = new DirectedEdge[graph.numberOfVertices()];
        distanceTo = new double[graph.numberOfVertices()];
        pq = new IndexMinPriorityQueue<>(graph.numberOfVertices());

        Arrays.fill(distanceTo, Double.MAX_VALUE);
        distanceTo[source] = 0;

        pq.insert(source, 0.0);
        while (!pq.isEmpty()) {
            for (DirectedEdge edge: graph.adjacentTo(pq.removeMin())) {
                relax(edge);
            }
        }
    }

    private void relax(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        double newDistance = distanceTo[v] + edge.weight();
        if (distanceTo[w] > newDistance) {
            distanceTo[w] = newDistance;
            edgeTo[w] = edge;
            if (pq.contains(w)) {
                pq.decreaseKey(w, newDistance);
            } else {
                pq.insert(w, newDistance);
            }
        }
    }

    public double distanceTo(int target) {
        return distanceTo[target];
    }

    public Iterable<DirectedEdge> pathTo(int target) {
        Stack<DirectedEdge> path = new ArrayStack<>();
        for (DirectedEdge edge = edgeTo[target]; edge != null; edge = edgeTo[edge.from()]) {
            path.push(edge);
        }
        return path;
    }

    public boolean hasPathTo(int target) {
        return distanceTo[target] < Double.MAX_VALUE;
    }
}
