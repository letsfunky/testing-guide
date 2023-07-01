package com.letsfunky.testingtype;


import org.junit.jupiter.api.Test;

import java.util.List;

class PriceEngineTest {

    // output-based test
    @Test
    public void 할인율을_확인한다() {
        var product1 = new Product("Hand wash");
        var product2 = new Product("Shampoo");
        var sut = new PriceEngine();

        double discount = sut.calculateDiscount(List.of(product1, product2));

        // TODO: assert

    }
}