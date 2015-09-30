package com.upit.algo.other;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DijkstraTwoStackCalculatorTest {
    private DijkstraTwoStackCalculator calculator = new DijkstraTwoStackCalculator();

    @Test
    public void shouldEvaluateSimpleExpression() {
        assertThat(calculator.evaluate("( 2 + 2 )"), is(4d));
    }

    @Test
    public void shouldEvaluateComplexExpression() {
        assertThat(calculator.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"), is(101d));
    }

}