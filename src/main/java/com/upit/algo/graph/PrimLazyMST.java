package com.upit.algo.graph;

import java.util.*;

public class PrimLazyMST implements MinSpanningTree {
    private boolean[] marked;
    private List<Edge> mst = new ArrayList<>();
    private PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

    public PrimLazyMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.numberOfVertices()];

        visit(graph, 0);

        while (!priorityQueue.isEmpty() && mst.size() < graph.numberOfVertices() - 1) {
            Edge edge = priorityQueue.remove();
            int v = edge.either();
            int w = edge.other(v);

            if (marked[v] && marked[w]) {
                continue;
            }

            mst.add(edge);
            int newVertex = marked[v] ? w : v;
            visit(graph, newVertex);
        }
    }

    private void visit(EdgeWeightedGraph graph, int vertex) {
        marked[vertex] = true;
        for (Edge edge: graph.adjacentTo(vertex)) {
            if (!marked[edge.other(vertex)]) {
                priorityQueue.add(edge);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }
}
