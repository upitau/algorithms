package com.upit.algo.graph;

import com.upit.algo.queue.ArrayQueue;
import com.upit.algo.queue.Queue;
import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

import java.util.Arrays;

public class BreadthFirstSearch implements Traversable {
    private static int DISCONNECTED = -1;

    private int source;
    private int[] distanceTo;
    private int[] edgeTo;

    public BreadthFirstSearch(Graph graph, int source) {
        this.source = source;
        this.distanceTo = new int[graph.numberOfVertices()];
        Arrays.fill(distanceTo, DISCONNECTED);
        this.edgeTo = new int[graph.numberOfVertices()];
        bfs(graph, source);
    }

    private void bfs(Graph graph, int source) {
        Queue<Integer> queue = new ArrayQueue<>();
        distanceTo[source] = 0;
        queue.enqueue(source);

        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            for (int adjacentVertex: graph.adjacentTo(vertex)) {
                if (!hasPathTo(adjacentVertex)) {
                    queue.enqueue(adjacentVertex);
                    distanceTo[adjacentVertex] = distanceTo[vertex] + 1;
                    edgeTo[adjacentVertex] = vertex;
                }
            }
        }
    }

    public boolean hasPathTo(int vertex) {
        return distanceTo[vertex] >= 0;
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
