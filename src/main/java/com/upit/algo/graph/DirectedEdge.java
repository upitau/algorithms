package com.upit.algo.graph;

public class DirectedEdge implements Comparable<DirectedEdge> {
    private final int from;
    private final int to;
    private final double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(DirectedEdge other) {
        if (weight < other.weight) {
            return -1;
        } else if (weight > other.weight) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectedEdge edge = (DirectedEdge) o;

        if (from != edge.from) return false;
        if (to != edge.to) return false;
        return Double.compare(edge.weight, weight) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = from;
        result = 31 * result + to;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
