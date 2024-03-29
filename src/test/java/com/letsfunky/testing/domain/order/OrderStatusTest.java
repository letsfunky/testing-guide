package com.letsfunky.testing.domain.order;

import org.junit.jupiter.api.Test;

import static com.letsfunky.testing.domain.order.OrderStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class OrderStatusTest {

    @Test
    void OrderStatus가_DRAFT일때_ORDERED로_변경가능하다() {
        assertThat(DRAFT.processableTo(OrderStatus.ORDERED)).isTrue();
    }

    @Test
    void ORDERED일때_PAYMENT_COMPLETED로_변경가능하다() {
        assertThat(OrderStatus.ORDERED.processableTo(OrderStatus.PAYMENT_COMPLETED)).isTrue();
    }

    @Test
    void ORDERED일때_SHIPPED로_변경가능하다() {
        assertThat(OrderStatus.ORDERED.processableTo(OrderStatus.SHIPPED)).isTrue();
    }

    @Test
    void OrderStatus가_DRAFT일때_PAYMENT_COMPLETED로_변경가능하다() {
        assertThat(DRAFT.processableTo(OrderStatus.PAYMENT_COMPLETED)).isTrue();
    }

    @Test
    void PAYMENT_SHIPPED일때_다른_상태로_변경이_불가하다() {
        // empty intended
    }
}