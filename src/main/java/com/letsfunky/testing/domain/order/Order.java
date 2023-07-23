package com.letsfunky.testing.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long ordererId;

    @Column(length = 20)
    private String shippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;

    public Order(long ordererId, String shippingAddress) {
        this.ordererId = ordererId;
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.DRAFT;
    }

    public Order(long id, long ordererId, String shippingAddress) {
        this.id = id;
        this.ordererId = ordererId;
        this.shippingAddress = shippingAddress;
        this.status = OrderStatus.DRAFT;
    }

    public Order updateStatus(OrderStatus nextStatus) {
        if (status.processableTo(nextStatus)) {
            this.status = nextStatus;
        }

        return this;
    }
}
