package com.letsfunky.testing.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.letsfunky.testing.application.store.StoreService;
import com.letsfunky.testing.domain.member.Member;
import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.domain.order.Order;
import com.letsfunky.testing.domain.order.OrderDetail;
import com.letsfunky.testing.domain.order.OrderRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RevisitedOrderServiceTest {

    private OrderRepository orderRepository;
    private MemberRepository memberRepository;
    private StoreService storeService;
    private OrderService sut;

    @BeforeEach
    void init() {
        orderRepository = mock(OrderRepository.class);
        memberRepository = mock(MemberRepository.class);
        storeService = mock(StoreService.class);
        sut = new OrderService(orderRepository, memberRepository, storeService);
    }

    @Test
    void 주문이_존재하면_주문상세를_가져온다() {
        var member = new Member(2L, "member-name");
        var order = new Order(1L, member.getId(), "shipping-address");
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        when(memberRepository.findById(member.getId())).thenReturn(Optional.of(member));

        var fetchedOrderDetail = sut.getOrderDetail(order.getId());

        assertEquals(member, order, fetchedOrderDetail);
    }

    private static void assertEquals(Member member, Order order, OrderDetail actual) {
        assertThat(actual.getOrderId()).isEqualTo(order.getId());
        assertThat(actual.getShippingAddress()).isEqualTo(order.getShippingAddress());
        assertThat(actual.getOrdererId()).isEqualTo(member.getId());
        assertThat(actual.getOrdererName()).isEqualTo(member.getName());

        /* assertion block
            assertThat(...)..
            assertThat(..)..
         */
    }

    @Test
    void 주문이_성공하면_inventory가_줄어든다() {
        var memberId = 12345L;
        var phoneNumber = "phoneNumber";
        var shippingAddress = "shippingAddress";
        var goods = "goods";
        var count = 3;
        var orderId = 321L;

        when(memberRepository.findById(memberId)).thenReturn(Optional.of(new Member(memberId, "member-name")));
        when(orderRepository.save(any(Order.class))).thenReturn(new Order(orderId, memberId, shippingAddress));
        when(storeService.hasInventory(goods, count)).thenReturn(true);

        var orderDetail = sut.createOrder(memberId, phoneNumber, shippingAddress, goods, count);

        assertThat(orderDetail.getOrderId()).isEqualTo(orderId);
        assertThat(orderDetail.getOrdererId()).isEqualTo(memberId);
        // assert goes on..
        verify(storeService, times(1)).removeInventory(goods, count);
    }
}

