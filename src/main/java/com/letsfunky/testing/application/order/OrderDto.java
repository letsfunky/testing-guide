package com.letsfunky.testing.application.order;

import com.letsfunky.testing.domain.order.OrderDetail;
import lombok.Value;

public class OrderDto {

    @Value
    public static class OrderRequest {
        long ordererId;
        String goods;
        int count;
        String phoneNumber;
        String shippingAddress;
    }

    @Value
    public static class OrderDetailResponse {
        long orderId;
        long ordererId;
        String ordererName;
        String shippingAddress;

        public static OrderDetailResponse of(OrderDetail orderDetail) {
            return new OrderDetailResponse(
                orderDetail.getOrderId(),
                orderDetail.getOrdererId(),
                orderDetail.getOrdererName(),
                orderDetail.getShippingAddress()
            );
        }
    }

    @Value
    public static class TestRequest {
        boolean isPrimitive;
        Boolean isBoxed;
    }

    @Value
    public static class TestResponse {
        boolean isPrimitive;
        Boolean isBoxed;
    }
}
