package com.letsfunky.testing.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderStatusTest {

    @Test
    void 주문상태가_변경된다() {
        assertThat(OrderStatus.DRAFT.processable(OrderStatus.ORDERED));
    }
}
