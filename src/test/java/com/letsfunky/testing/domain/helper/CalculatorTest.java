package com.letsfunky.testing.domain.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void given_two_arbitrary_integers_then_sum_should_be_equal_to_the_sum_of_given_integers() {
        // arrange
        int x = 11_235;
        int y = 24_322;

        // act
        var actual = Calculator.sum(x, y);

        // assert
        int expected = x + y;
        assertEquals(actual, expected);
    }

    @Test
    void given_two_integers_max_value_then_sum_should_be_equal_to_the_sum_of_given_integers() {
        // arrange
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;

        // act
        var actualResult = Calculator.sum(x, y);

        // assert
        int expectedResult = x + y;
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @DisplayName("Integer.MIN_VALUE 두개를 sum()하면 두 숫자를 더한 것과 결과값이 같아야 한다.")
    void sum_of_integer_min_value() {
        // arrange
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;

        // act
        var actualResult = Calculator.sum(x, y);

        // assert
        int expectedResult = x + y;
        assertEquals(actualResult, expectedResult);
    }
}
