package com.upit.algo.graph;

import java.util.Arrays;

public class ConnectedComponents {
    private static int UNDEFINED = -1;

    private int[] id;
    private int count;

    public ConnectedComponents(UndirectedGraph graph) {
        id = new int[graph.numberOfVertices()];
        Arrays.fill(id, UNDEFINED);

        for (int vertex = 0; vertex < graph.numberOfVertices(); vertex++) {
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

    private void dfs(UndirectedGraph graph, int vertex) {
        id[vertex] = count;
        for (int adjacentVertex: graph.adjacentTo(vertex)) {
            if (id[adjacentVertex] == UNDEFINED) {
                dfs(graph, adjacentVertex);
            }
        }
    }

}