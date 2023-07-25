package com.letsfunky.testing.domain.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RevisitedCalculatorTest {

    @Nested
    class sum_테스트 {

        // @ParameterizedTest 사용
        @ParameterizedTest
        @MethodSource("sumIntegersSource")
        void integer를_sum한다(int x, int y, long expected) {
            var actual = RevisitedCalculator.sum(x, y);

            assertThat(actual).isEqualTo(expected);
        }

        static Stream<Arguments> sumIntegersSource() {
            return Stream.of(
                    Arguments.arguments(123_456, 789_012, 912_468),
                    Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, 4294967294L),
                    Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, -4294967296L)
            );
        }

        // @ParameterizedTest 미사용
        @Test
        void integer를_sum한다() {
            Map.of(
                    List.of(123_456, 789_012), 912_468L,
                    List.of(Integer.MAX_VALUE, Integer.MAX_VALUE), 4294967294L,
                    List.of(Integer.MIN_VALUE, Integer.MIN_VALUE), -4294967296L
            ).forEach((xy, expected) -> {
                var x = xy.get(0);
                var y = xy.get(1);

                var actual = RevisitedCalculator.sum(x, y);

                assertThat(actual).isEqualTo(expected);
            });
        }
    }

    @Nested
    class invert_테스트 {

        @Test
        void double은_나눗셈한다() {
            var doubleStr = "3.0";
            var expectedStartsWith = "0.3333";
            var expectedDerivedFrom = String.valueOf(1.0 / Double.parseDouble(doubleStr));

            var result = Calculator.invert(doubleStr);

            assertThat(result).isEqualTo(expectedDerivedFrom);
            assertThat(result).contains(expectedStartsWith);
        }

        @Test
        void double이_아니면_reverse한다() {
            var doubleStr = "3.0abc";
            var expectedReversed = "cba0.3";
            var expectedDerivedFrom = new StringBuilder(doubleStr).reverse().toString();

            var result = Calculator.invert(doubleStr);

            assertThat(result).isEqualTo(expectedDerivedFrom);
            assertThat(result).contains(expectedReversed);
        }
    }
}
