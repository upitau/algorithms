package com.upit.algo.graph;

import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

public class DepthFirstSearch implements Traversable {
    private int source;
    private boolean[] marked;
    private int[] edgeTo;

    public DepthFirstSearch(Graph graph, int source) {
        this.source = source;
        this.marked = new boolean[graph.numberOfVertices()];
        this.edgeTo = new int[graph.numberOfVertices()];
        dfs(graph, source);
    }

    private void dfs(Graph graph, int vertex) {
        marked[vertex] = true;
        for (int adjacentVertex: graph.adjacentTo(vertex)) {
            if (!marked[adjacentVertex]) {
                edgeTo[adjacentVertex] = vertex;
                dfs(graph, adjacentVertex);
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }

    public Iterable<Integer> pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return null;
        }

        Stack<Integer> path = new ArrayStack<>();
        for (int v = vertex; v != source; v = edgeTo[v]) {
            path.push(v);
        }
        path.push(source);

        return path;
    }
}
