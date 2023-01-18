package com.letsfunky.testing.domain.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RevisitedCalculatorTest {

    @ParameterizedTest
    @MethodSource("sumIntegersSource")
    void 두개의_integer를_sum한다(int x, int y, long expected) {
        long actual = RevisitedCalculator.sum(x, y);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> sumIntegersSource() {
        return Stream.of(
            Arguments.arguments(123_456, 789_012, 912_468),
            Arguments.arguments(Integer.MAX_VALUE, Integer.MAX_VALUE, 4294967294L),
            Arguments.arguments(Integer.MIN_VALUE, Integer.MIN_VALUE, -4294967296L)
        );
    }

    @Test
    void 두개의_integer를_sum한다_$parameterized_test_미사용$() {
        Map.of(
            List.of(123_456, 789_012), 912_468L,
            List.of(Integer.MAX_VALUE, Integer.MAX_VALUE), 4294967294L,
            List.of(Integer.MIN_VALUE, Integer.MIN_VALUE), -4294967296L
        ).forEach((k, expected) -> {
            var x = k.get(0);
            var y = k.get(1);

            long actual = RevisitedCalculator.sum(x, y);

            assertThat(actual).isEqualTo(expected);
        });
    }
}
