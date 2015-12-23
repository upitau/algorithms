package com.upit.algo.graph;

import com.upit.algo.queue.ArrayQueue;
import com.upit.algo.queue.Queue;
import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

import java.util.Arrays;

public class BellmanFordShortestPaths implements DirectedTraversable {
    private DirectedEdge[] edgeTo;
    private double[] distanceTo;
    private Queue<Integer> vertices = new ArrayQueue<>();
    private boolean[] enqueuedVertices;

    public BellmanFordShortestPaths(EdgeWeightedDigraph graph) {
        edgeTo = new DirectedEdge[graph.numberOfVertices()];
        distanceTo = new double[graph.numberOfVertices()];
        enqueuedVertices = new boolean[graph.numberOfVertices()];

        Arrays.fill(distanceTo, Double.MAX_VALUE);
        distanceTo[0] = 0;

        for (int vertex = 0; vertex < graph.numberOfVertices(); vertex++) {
            vertices.enqueue(vertex);
            enqueuedVertices[vertex] = true;
        }

        while (!vertices.isEmpty()) {
            int vertex = vertices.dequeue();
            enqueuedVertices[vertex] = false;
            for (DirectedEdge edge: graph.adjacentTo(vertex)) {
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
            if (!enqueuedVertices[w]) {
                vertices.enqueue(w);
                enqueuedVertices[w] = true;
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
