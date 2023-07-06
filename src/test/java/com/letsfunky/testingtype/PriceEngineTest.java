package com.letsfunky.testingtype;


import org.junit.jupiter.api.Test;

import java.util.List;

class PriceEngineTest {

    // output-based test
    // todo: write test `최소_할인율을_확인한다`


    @Test
    public void 최대_할인율을_확인한다() {
        // todo: implement builder
        var product1 = new Product("Hand wash");
        var product2 = new Product("Shampoo");
        // ...
        var product51 = new Product("blabla");

        double discount = PriceEngine.calculateDiscount(List.of(product1, product2, product51));

        // TODO: assert

    }
}
