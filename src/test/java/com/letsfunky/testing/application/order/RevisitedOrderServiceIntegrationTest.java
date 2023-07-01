package com.letsfunky.testing.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.letsfunky.testing.domain.member.Member;
import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.domain.order.OrderRepository;
import com.letsfunky.testing.infrastructure.message.SmsService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RevisitedOrderServiceIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @MockBean
    private SmsService smsService;

    @Autowired
    private RevisitedOrderService sut;

    @Test
    void 주문이_성공하면_주문이_저장되고_sms가_발송된다() {
        var member = memberRepository.saveAndFlush(new Member("member-name"));
        // NOTE: implies method parameters need refactoring
        var phoneNumber = "phoneNumber";
        var shippingAddress = "shippingAddress";
        var goods = "goods";
        var count = 3;

        var orderDetail = sut.createOrder(member, phoneNumber, shippingAddress, goods, count);

        verify(smsService, times(1)).send(eq(phoneNumber), any());
        assertThat(orderRepository.findAll().size()).isEqualTo(1);
        assertThat(orderDetail.getOrdererId()).isEqualTo(member.getId());
    }
}
