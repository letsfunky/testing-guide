package com.letsfunky.testing.domain.order;

import org.junit.jupiter.api.Test;

import static com.letsfunky.testing.domain.order.OrderStatus.*;
import static com.letsfunky.testing.domain.order.OrderStatus.SHIPPED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void 주문상태가_변경된다() {
        // arrange
        var order = new Order(1L, "address");
        assertThat(order.getStatus()).isEqualTo(DRAFT);

        // act
        var orderdOrder = order.updateStatus(ORDERED);
        // assert
        assertThat(order.getStatus()).isEqualTo(ORDERED);

        // act
        var paidOrder = orderdOrder.updateStatus(PAYMENT_COMPLETED);
        // assert
        assertThat(order.getStatus()).isEqualTo(PAYMENT_COMPLETED);

        // act
        var shippedOrder = paidOrder.updateStatus(SHIPPED);
        // assert
        assertThat(order.getStatus()).isEqualTo(SHIPPED);
    }
}
