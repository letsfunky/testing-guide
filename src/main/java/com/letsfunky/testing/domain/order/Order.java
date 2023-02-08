package com.letsfunky.testing.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Order(long ordererId, String shippingAddress) {
        this.ordererId = ordererId;
        this.shippingAddress = shippingAddress;
    }
}
