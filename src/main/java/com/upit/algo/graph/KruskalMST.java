package com.upit.algo.graph;

import com.upit.algo.unionfind.QuickUnion;
import com.upit.algo.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMST implements MinSpanningTree {
    private List<Edge> mst = new ArrayList<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (Edge edge: graph.edges()) {
            queue.add(edge);
        }

        UnionFind unionFind = new QuickUnion(graph.numberOfVertices());
        while(!queue.isEmpty() && mst.size() < graph.numberOfVertices() - 1) {
            Edge edge = queue.remove();
            int v = edge.either();
            int w = edge.other(v);
            if (!unionFind.connected(v, w)) {
                unionFind.union(v, w);
                mst.add(edge);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }
}
