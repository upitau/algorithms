package com.upit.algo.graph;

import com.upit.algo.queue.ArrayQueue;
import com.upit.algo.queue.Queue;

public class FordFulkersonMaxFlow {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkersonMaxFlow(FlowNetwork graph, int source, int target) {
        value = 0.0;
        while (hasAugmentingPath(graph, source, target)) {
            double bottleneck = Double.MAX_VALUE;
            for (int vertex = target; vertex != source; vertex = edgeTo[vertex].other(vertex)) {
                bottleneck = Math.min(bottleneck, edgeTo[vertex].residualCapacityTo(vertex));
            }
            for (int vertex = target; vertex != source; vertex = edgeTo[vertex].other(vertex)) {
                edgeTo[vertex].addResidualFlowTo(vertex, bottleneck);
            }
            value += bottleneck;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork graph, int source, int target) {
        marked = new boolean[graph.numberOfVertices()];
        edgeTo = new FlowEdge[graph.numberOfVertices()];

        Queue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(source);
        marked[source] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            marked[vertex] = true;
            for (FlowEdge edge: graph.adjacentTo(vertex)) {
                int other = edge.other(vertex);
                if (!marked[other] && edge.residualCapacityTo(other) > 0) {
                    marked[other] = true;
                    edgeTo[other] = edge;
                    queue.enqueue(other);
                }
            }
        }
        return marked[target];
    }

    public double value() {
        return value;
    }

    public boolean inCut(int vertex) {
        return marked[vertex];
    }
}
