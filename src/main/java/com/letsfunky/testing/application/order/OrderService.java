package com.letsfunky.testing.application.order;

import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.domain.order.OrderDetail;
import com.letsfunky.testing.domain.order.OrderRepository;
import com.letsfunky.testing.infrastructure.message.SmsApiClient;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final SmsApiClient smsApiClient;

    public OrderService(
        OrderRepository orderRepository,
        MemberRepository memberRepository,
        SmsApiClient smsApiClient
    ) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.smsApiClient = smsApiClient;
    }

    public OrderDetail getOrderDetail(long orderId) {
        /*
            TODO: needs query abstraction
            TODO: needs exception abstraction
         */
        return orderRepository.findById(orderId).map(order ->
                memberRepository.findById(order.getOrdererId()).map(member ->
                    OrderDetail.of(member, order)
                ).orElseThrow(() -> new RuntimeException("member not exist, ordererId=" + order.getOrdererId()))
        ).orElseThrow(
            () -> new RuntimeException("order not exist, orderId=" + orderId));
    }
}
