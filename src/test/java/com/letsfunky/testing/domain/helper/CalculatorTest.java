package com.letsfunky.testing.domain.helper;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Nested
    class sum_테스트 {

        @Test
        void add_two_integers() {
            var x = 1;
            var y = 2;
            var expected = 3;
            var derivedExpected = x + y;

            var result = Calculator.sum(x, y);

            assertThat(result).isEqualTo(expected);
        }
    }

    @Nested
    class invert_테스트 {

        @Test
        void
    }
}
