package com.upit.algo.other;

import com.upit.algo.stack.ArrayStack;
import com.upit.algo.stack.Stack;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DijkstraTwoStackCalculator {
    private static Set<String> OPERATORS = Stream.of("+", "-", "*", "/").collect(Collectors.toSet());

    public double evaluate(String expr) {
        Stack<Double> valueStack = new ArrayStack<>();
        Stack<String> operatorStack = new ArrayStack<>();

        for (String token: expr.split(" ")) {
            if ("(".equals(token)) {
            } else if (OPERATORS.contains(token)) {
                operatorStack.push(token);
            } else if (")".equals(token)) {
                double operand2 = valueStack.pop();
                double operand1 = valueStack.pop();
                String operator = operatorStack.pop();
                if ("+".equals(operator)) {
                    valueStack.push(operand1 + operand2);
                } else if ("-".equals(operator)) {
                    valueStack.push(operand1 - operand2);
                } else if ("*".equals(operator)) {
                    valueStack.push(operand1 * operand2);
                } else {
                    valueStack.push(operand1 / operand2);
                }
            } else {
                valueStack.push(Double.valueOf(token));
            }
        }
        return valueStack.pop();
    }
}
