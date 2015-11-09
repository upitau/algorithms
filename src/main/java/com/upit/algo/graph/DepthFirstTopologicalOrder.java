package com.upit.algo.graph;

import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

public class DepthFirstTopologicalOrder {
    private boolean[] marked;
    private Stack<Integer> reverseOrder = new ArrayStack<>();

    public DepthFirstTopologicalOrder(Digraph graph) {
        marked = new boolean[graph.numberOfVertices()];
        for (int vertex = 0; vertex < graph.numberOfVertices(); vertex++) {
            if (!marked[vertex]) {
                dfs(graph, vertex);
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
        reverseOrder.push(vertex);
    }

    public Iterable<Integer> inTopologicalOrder() {
        return reverseOrder;
    }
}
