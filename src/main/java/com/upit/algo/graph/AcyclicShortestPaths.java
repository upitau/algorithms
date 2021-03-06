package com.upit.algo.graph;

import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

import java.util.Arrays;

public class AcyclicShortestPaths implements DirectedTraversable {
    private DirectedEdge[] edgeTo;
    private double[] distanceTo;

    public AcyclicShortestPaths(EdgeWeightedDigraph graph, int source) {
        edgeTo = new DirectedEdge[graph.numberOfVertices()];
        distanceTo = new double[graph.numberOfVertices()];

        Arrays.fill(distanceTo, Double.MAX_VALUE);
        distanceTo[source] = 0;

        DepthFirstTopologicalOrder order = new DepthFirstTopologicalOrder(toDigraph(graph));
        for (int vertex: order.inTopologicalOrder()) {
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


    private Digraph toDigraph(EdgeWeightedDigraph graph) {
        Digraph result = new Digraph(graph.numberOfVertices());
        for (int vertex = 0; vertex < graph.numberOfVertices(); vertex++) {
            for (DirectedEdge edge: graph.adjacentTo(vertex)) {
                result.addEdge(edge.from(), edge.to());
            }
        }
        return result;
    }
 }
