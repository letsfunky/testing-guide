package com.letsfunky.testingstyle;

import org.junit.jupiter.api.Test;

// state-based test
class OrderTest {

    @Test
    void 주문에_제품을_추가하고_정상적으로_추가되었는지_확인한다() {
        // arrange
        var product = new Product("foo");
        var sut = new Order();

        // act
        sut.addProduct(product);

        // assert
    }
}