package com.letsfunky.testingstyle;

import org.junit.jupiter.api.Test;

import static com.letsfunky.testingstyle.RevisitedProductBuilder.dummyProduct;
import static org.assertj.core.api.Assertions.assertThat;

// state-based test
class RevisitedOrderTest {

    @Test
    void 주문에_제품을_추가한다() {
        // todo: arrange
        //  1. create Product fixture
        //  2. create system under test (Order)
        var product = dummyProduct();
        var sut = new Order();

        // todo:act
        //  1. add product to order
        sut.addProduct(product);

        // todo: assert
        //  1. assert order products has size 1
        //  2. assert product equals to given fixture
        assertThat(sut.products()).hasSize(1);
        assertThat(sut.products().get(0)).isEqualTo(product);
    }
}
