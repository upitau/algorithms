package com.upit.algo.graph;

import java.util.NoSuchElementException;

public class FlowEdge {
    private final int from;
    private final int to;
    private final double capacity;
    private double flow;

    public FlowEdge(int from, int to, double capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public int other(int vertex) {
        checkVertex(vertex);
        return vertex == from ? to : from;
    }

    public double residualCapacityTo(int vertex) {
        checkVertex(vertex);
        return vertex == from ? flow : capacity - flow;
    }

    public void addResidualFlowTo(int vertex, double delta) {
        checkVertex(vertex);
        if (vertex == from) {
            flow -= delta;
        } else {
            flow += delta;
        }
    }

    private void checkVertex(int vertex) {
        if (vertex != from && vertex != to) {
            throw new IllegalArgumentException("Unknown vertex: " + vertex);
        }
    }
}
