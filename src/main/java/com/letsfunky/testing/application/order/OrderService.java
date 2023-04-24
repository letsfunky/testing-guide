package com.letsfunky.testing.application.order;

import com.letsfunky.testing.application.store.StoreService;
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
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final StoreService storeService;
    private final SmsService smsService;

    public OrderService(
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

    @Transactional(readOnly = true)
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

    @Transactional
    public OrderDetail createOrder(
        // NOTE: too many params, need refactoring
        long memberId,
        String phoneNumber, String shippingAddress,
        String goods, int count
    ) {
        if (storeService.hasInventory(goods, count)) {
            storeService.removeInventory(goods, count);

            var order = new Order(memberId, shippingAddress);
            var persistedOrder = orderRepository.save(order);
            var member = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("member not exist: memberId=" + memberId)
            );

            smsService.send(phoneNumber, "order placed");

            log.info("order created. orderId={}", persistedOrder.getId());
            return OrderDetail.of(member, persistedOrder);
        } else {
            throw new RuntimeException("not enough inventory");
        }
    }
}
