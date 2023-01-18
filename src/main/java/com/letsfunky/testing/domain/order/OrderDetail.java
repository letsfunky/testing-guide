package com.letsfunky.testing.domain.order;

import com.letsfunky.testing.domain.member.Member;
import lombok.Value;

@Value
public class OrderDetail {

    long orderId;
    long ordererId;
    String ordererName;
    String shippingAddress;

    public static OrderDetail of(Member member, Order order) {
        return new OrderDetail(
            order.getId(),
            order.getOrdererId(),
            member.getName(),
            order.getShippingAddress()
        );
    }
}
