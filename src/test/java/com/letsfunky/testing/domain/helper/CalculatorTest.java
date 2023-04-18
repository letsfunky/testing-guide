package com.letsfunky.testing.domain.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void given_two_arbitrary_integers_then_sum_should_be_equal_to_the_sum_of_given_integers() {
        var x = 11_235;
        var y = 24_322;

        var actual = Calculator.sum(x, y);

        var derivedExpected = x + y;
        assertEquals(actual, derivedExpected);

        var expected = 35_557;
        // assertEquals(actual, expected);
    }

    @Test
    void given_two_integers_max_value_then_sum_should_be_equal_to_the_sum_of_given_integers() {
        // arrange
        var x = Integer.MAX_VALUE;
        var y = Integer.MAX_VALUE;

        // act
        var actual = Calculator.sum(x, y);

        // assert
        var derivedExpected = x + y;
        assertEquals(actual, derivedExpected);

        var expected = 4294967294L;
        // assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Integer.MIN_VALUE 두개를 sum하면 두 숫자를 더한 것과 결과값이 같아야 한다.")
    void sum_of_integer_min_value() {
        // arrange
        var x = Integer.MIN_VALUE;
        var y = Integer.MIN_VALUE;

        // act
        var actual = Calculator.sum(x, y);

        // assert
        var derivedExpected = x + y;
        assertEquals(actual, derivedExpected);

        var expected = -4294967296L;
        // assertEquals(actual, expected);
    }
}
