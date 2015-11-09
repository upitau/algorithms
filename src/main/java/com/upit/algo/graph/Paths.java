package com.upit.algo.graph;

public interface Paths {

    boolean hasPathTo(int vertex);

    Iterable<Integer> pathTo(int vertex);
}
