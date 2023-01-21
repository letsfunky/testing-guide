package com.letsfunky.testing.domain.order;

import java.util.Set;

public enum OrderStatus {

    DRAFT,
    ORDERED,
    PAYMENT_COMPLETED,
    SHIPPED;

    // NOTE: 의미없는 dummy logic 입니다
    public boolean processable(OrderStatus nextStatus) {
        switch (this) {
            case DRAFT:
                return Set.of(ORDERED, PAYMENT_COMPLETED).contains(nextStatus);
            case ORDERED:
                return Set.of(PAYMENT_COMPLETED, SHIPPED).contains(nextStatus);
            case PAYMENT_COMPLETED:
            case SHIPPED:
            default:
                return false;
        }
    }

}
