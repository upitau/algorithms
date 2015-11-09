package com.upit.algo.graph;

import com.upit.algo.unionfind.QuickUnion;
import com.upit.algo.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KruskalMinimumSpanningTree implements MinimumSpanningTree {
    private UnionFind unionFind;
    private List<Edge> mst = new ArrayList<>();

    public KruskalMinimumSpanningTree(EdgeWeightedGraph graph) {
        unionFind = new QuickUnion(graph.numberOfVertices());
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for (Edge edge: graph.edges()) {
            queue.add(edge);
        }

        while(mst.size() < graph.numberOfVertices() - 1) {
            Edge edge = queue.poll();
            int v = edge.either();
            int w = edge.other(v);
            if (!unionFind.connected(v, w)) {
                mst.add(edge);
                unionFind.union(v, w);
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return mst;
    }

    @Override
    public double weight() {
        double weight = 0;
        for (Edge edge: edges()) {
            weight += edge.weight();
        }
        return weight;
    }
}
