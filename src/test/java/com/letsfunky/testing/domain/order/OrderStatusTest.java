package com.letsfunky.testing.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderStatusTest {

    @Nested
    class OrderStatus가_DRAFT일때 {
        @Test
        void ORDERED로_변경가능하다() {
            assertThat(OrderStatus.DRAFT.processable(OrderStatus.ORDERED)).isTrue();
        }

        @Test
        void PAYMENT_COMPLETED로_변경가능하다() {
            assertThat(OrderStatus.DRAFT.processable(OrderStatus.PAYMENT_COMPLETED)).isTrue();
        }
    }

    @Nested
    class OrderStatus가_ORDERED일때 {
        @Test
        void PAYMENT_COMPLETED로_변경가능하다() {
            assertThat(OrderStatus.ORDERED.processable(OrderStatus.PAYMENT_COMPLETED)).isTrue();
        }

        @Test
        void SHIPPED로_변경가능하다() {
            assertThat(OrderStatus.ORDERED.processable(OrderStatus.SHIPPED)).isTrue();
        }
    }

    @Nested
    class OrderStatus가_PAYMENT_SHIPPED일때 {
        @Test
        void 다른_상태로_변경이_불가하다() {
            throw new UnsupportedOperationException("test not implemented yet");
        }
    }
}
