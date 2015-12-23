package com.upit.algo.graph;

public interface Traversable {

    boolean hasPathTo(int vertex);

    Iterable<Integer> pathTo(int vertex);
}
