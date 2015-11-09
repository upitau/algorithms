package com.upit.algo.graph;

public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        return vertex == v ? w : v;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        if (weight < other.weight) {
            return -1;
        } else if (weight > other.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
