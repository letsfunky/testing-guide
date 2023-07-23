package com.letsfunky.testing.domain.order;

import static com.letsfunky.testing.domain.order.OrderStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RevisitedOrderStatusTest {

    @Nested
    class DRAFT일때 {
        @Test
        void ORDERED로_변경가능하다() {
            assertThat(DRAFT.processableTo(ORDERED)).isTrue();
        }

        @Test
        void PAYMENT_COMPLETED로_변경가능하다() {
            assertThat(DRAFT.processableTo(PAYMENT_COMPLETED)).isTrue();
        }
    }

    @Nested
    class ORDERED일때 {
        @Test
        void PAYMENT_COMPLETED로_변경가능하다() {
            assertThat(ORDERED.processableTo(PAYMENT_COMPLETED)).isTrue();
        }

        @Test
        void SHIPPED로_변경가능하다() {
            assertThat(ORDERED.processableTo(SHIPPED)).isTrue();
        }
    }

    @Nested
    class PAYMENT_SHIPPED일때 {
        @Test
        void 다른_상태로_변경이_불가하다() {
            // empty intended
        }
    }
}
