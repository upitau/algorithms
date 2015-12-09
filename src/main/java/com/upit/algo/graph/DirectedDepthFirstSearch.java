package com.upit.algo.graph;

public class DirectedDepthFirstSearch {
    private boolean[] marked;

    public DirectedDepthFirstSearch(Digraph graph, int source) {
        this.marked = new boolean[graph.numberOfVertices()];
        dfs(graph, source);
    }

    public DirectedDepthFirstSearch(Digraph graph, Iterable<Integer> sources) {
        this.marked = new boolean[graph.numberOfVertices()];
        for (Integer source: sources) {
            if (!marked[source]) {
                dfs(graph, source);
            }
        }
    }

    private void dfs(Digraph graph, int vertex) {
        marked[vertex] = true;
        for (int adjacentVertex: graph.adjacentTo(vertex)) {
            if (!marked[adjacentVertex]) {
                dfs(graph, adjacentVertex);
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }
}
