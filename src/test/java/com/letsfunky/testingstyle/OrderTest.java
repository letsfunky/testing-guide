package com.letsfunky.testingstyle;

import org.junit.jupiter.api.Test;

// state-based test
class OrderTest {

    @Test
    void 주문에_제품을_추가한다() {
        // arrange
        var product = new Product("foo");
        var sut = new Order();

        // act
        sut.addProduct(product);

        // assert
    }
}