package com.letsfunky.testing.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderRepositoryIntegrationTest {

    @Autowired
    private OrderRepository sut;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void order를_저장한다() {
        assertThat(sut.findAll().size()).isEqualTo(0);

        var order = sut.saveAndFlush(new Order(1L, "01234567890123456789"));
        testEntityManager.flush();

        sut.findById(order.getId()).map(fetchedOrder ->
            assertThat(fetchedOrder.getShippingAddress()).isEqualTo(order.getShippingAddress())
        ).orElseThrow(() -> new IllegalStateException("should not reach here"));
    }
}
