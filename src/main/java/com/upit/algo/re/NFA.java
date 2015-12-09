package com.upit.algo.re;

import com.upit.algo.graph.Digraph;
import com.upit.algo.graph.DirectedDepthFirstSearch;
import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NFA {
    private char[] re;
    private Digraph epsilonTransitions;
    private int m;

    public NFA(String regexp) {
        m = regexp.length();
        re = regexp.toCharArray();
        epsilonTransitions = buildEpsilonTransitionGraph();
    }

    private Digraph buildEpsilonTransitionGraph() {
        Digraph g = new Digraph(m + 1);
        Stack<Integer> ops = new ArrayStack<>();

        for (int i = 0; i < m; i++) {
            int closureLeft = i;

            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                Stack<Integer> orIndexes = new ArrayStack<>();
                closureLeft = ops.pop();
                while (re[closureLeft] == '|') {
                    orIndexes.push(closureLeft);
                    closureLeft = ops.pop();
                }
                for (int orIndex: orIndexes) {
                    g.addEdge(closureLeft, orIndex + 1);
                    g.addEdge(orIndex, i);
                }
            }

            if (i + 1 < m && re[i + 1] == '*') {
                g.addEdge(closureLeft, i + 1);
                g.addEdge(i + 1, closureLeft);
            }

            if (re[i] == '(' || re[i] == '*' || re[i] == ')') {
                g.addEdge(i, i + 1);
            }
        }

        return g;
    }

    public boolean recognizes(String text) {
        Set<Integer> state = applyEpsilonTransitions(Collections.singleton(0));

        for (char ch: text.toCharArray()) {
            if (state.isEmpty()) {
                return false;
            }
            state = applyEpsilonTransitions(applyMatchingTransitions(state, ch));
        }

        return state.contains(m);
    }

    private Set<Integer> applyEpsilonTransitions(Set<Integer> state) {
        DirectedDepthFirstSearch dfs = new DirectedDepthFirstSearch(epsilonTransitions, state);
        Set<Integer> newState = new HashSet<>();
        for (int vertex = 0; vertex <= m; vertex++) {
            if (dfs.hasPathTo(vertex)) {
                newState.add(vertex);
            }
        }
        return newState;
    }

    private Set<Integer> applyMatchingTransitions(Set<Integer> state, char ch) {
        Set<Integer> matched = new HashSet<>();
        for (int vertex: state) {
            if (vertex < m && (re[vertex] == ch || re[vertex] == '.')) {
                matched.add(vertex + 1);
            }
        }
        return matched;
    }

}
