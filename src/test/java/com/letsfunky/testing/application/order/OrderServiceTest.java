package com.letsfunky.testing.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.letsfunky.testing.application.store.StoreService;
import com.letsfunky.testing.domain.member.Member;
import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.domain.order.Order;
import com.letsfunky.testing.domain.order.OrderRepository;
import com.letsfunky.testing.infrastructure.message.SmsApiService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderServiceTest {

    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private StoreService storeService;
    private SmsApiService smsApiService;
    private OrderService orderService;

    @BeforeEach
    void init() {
        orderRepository = mock(OrderRepository.class);
        memberRepository = mock(MemberRepository.class);
        storeService = mock(StoreService.class);
        smsApiService = mock(SmsApiService.class);
        orderService = new OrderService(orderRepository, memberRepository, storeService, smsApiService);
    }

    @Test
    void 주문이_존재하면_주문상세를_가져온다() {
        var order = new Order(1L, 1L, "shipping-address");
        var member = new Member(1L, "member-name");
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));

        var actual = orderService.getOrderDetail(order.getId());

        assertThat(actual.getOrderId()).isEqualTo(order.getId());
        assertThat(actual.getShippingAddress()).isEqualTo(order.getShippingAddress());
        assertThat(actual.getOrdererId()).isEqualTo(member.getId());
        assertThat(actual.getOrdererName()).isEqualTo(member.getName());
    }
}
