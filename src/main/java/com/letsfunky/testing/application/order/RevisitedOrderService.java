package com.letsfunky.testing.application.order;

import com.letsfunky.testing.application.store.StoreService;
import com.letsfunky.testing.domain.member.Member;
import com.letsfunky.testing.domain.member.MemberRepository;
import com.letsfunky.testing.domain.order.Order;
import com.letsfunky.testing.domain.order.OrderDetail;
import com.letsfunky.testing.domain.order.OrderRepository;
import com.letsfunky.testing.infrastructure.message.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RevisitedOrderService {

    private final OrderRepository orderRepository; // managed dependency
    private final MemberRepository memberRepository; // managed dependency
    private final StoreService storeService; // managed dependency
    private final SmsService smsService; // unmanaged dependency

    public RevisitedOrderService(
        OrderRepository orderRepository,
        MemberRepository memberRepository,
        StoreService storeService,
        SmsService smsService
    ) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.storeService = storeService;
        this.smsService = smsService;
    }

    @Transactional
    public OrderDetail createOrder(
        Member member,
        String phoneNumber, String shippingAddress,
        String goods, int count
    ) {
        if (storeService.hasInventory(goods, count)) {
            storeService.removeInventory(goods, count);

            var order = new Order(member.getId(), shippingAddress);
            var persistedOrder = orderRepository.save(order);

            smsService.send(phoneNumber, "order placed successfully.");
            log.info("order created: orderId={}", persistedOrder.getId());

            return OrderDetail.of(member, persistedOrder);
        } else {
            throw new RuntimeException("not enough inventory"); // needs exception abstraction
        }
    }

    @Transactional(readOnly = true)
    public OrderDetail getOrderDetail(long orderId) {
        var order = orderRepository.findById(orderId).get();
        var member = memberRepository.findById(order.getOrdererId()).get();

        return OrderDetail.of(member, order);
    }
}
