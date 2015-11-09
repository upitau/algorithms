package com.upit.algo.graph;

import com.upit.algo.stack.Stack;

import java.util.Arrays;

public class KosarajuSharirStronglyConnectedComponents {
    private static int UNDEFINED = -1;

    private int[] id;
    private int count;

    public KosarajuSharirStronglyConnectedComponents(DirectedGraph graph) {
        id = new int[graph.numberOfVertices()];
        Arrays.fill(id, UNDEFINED);

        for (int vertex: new DepthFirstTopologicalOrder(graph.reverse()).inTopologicalOrder()) {
            if (id[vertex] == UNDEFINED) {
                dfs(graph, vertex);
                count++;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public int getId(int vertex) {
        return id[vertex];
    }

    private void dfs(DirectedGraph graph, int vertex) {
        id[vertex] = count;
        for (int adjacentVertex: graph.adjacentTo(vertex)) {
            if (id[adjacentVertex] == UNDEFINED) {
                dfs(graph, adjacentVertex);
            }
        }
    }

}