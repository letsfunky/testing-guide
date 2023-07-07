package com.letsfunky.testingstyle;


import org.junit.jupiter.api.Test;

// output-based test
class PriceEngineTest {

    // todo: write test `최소_할인율을_확인한다`
    void 최소_할인율을_확인한다() {
        // arrange
        var product = new Product("Hand wash");

        // todo: act

        // todo: assert
    }

    // todo: builder 를 만들어보자
    @Test
    public void 최대_할인율을_확인한다() {
        // arrange
        // todo: implement builder
        var product1 = new Product("Hand wash");
        var product2 = new Product("Shampoo");
        // 총 51개...
        var product51 = new Product("blabla");

        // todo: act

        // todo: assert

    }

}
