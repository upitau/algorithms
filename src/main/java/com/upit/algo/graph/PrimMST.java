package com.upit.algo.graph;

import com.upit.algo.queue.IndexMinPriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class PrimMST implements MinSpanningTree {
    private boolean[] marked;
    private List<Edge> mst = new ArrayList<>();
    private IndexMinPriorityQueue<Edge> indexPQ;

    public PrimMST(EdgeWeightedGraph graph) {
        marked = new boolean[graph.numberOfVertices()];
        indexPQ = new IndexMinPriorityQueue<>(graph.numberOfVertices());

        visit(graph, 0);

        while (!indexPQ.isEmpty()) {
            mst.add(indexPQ.minKey());
            int vertex = indexPQ.removeMin();
            visit(graph, vertex);
        }
    }

    private void visit(EdgeWeightedGraph graph, int vertex) {
        marked[vertex] = true;
        for (Edge edge: graph.adjacentTo(vertex)) {
            int other = edge.other(vertex);
            if (!marked[other]) {
                if (!indexPQ.contains(other)) {
                    indexPQ.insert(other, edge);
                } else if (edge.compareTo(indexPQ.keyOf(other)) < 0) {
                    indexPQ.decreaseKey(other, edge);
                }
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }
}
