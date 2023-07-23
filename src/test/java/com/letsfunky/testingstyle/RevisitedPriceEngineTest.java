package com.letsfunky.testingstyle;


import org.junit.jupiter.api.Test;

import java.util.List;

import static com.letsfunky.testingstyle.RevisitedProductBuilder.generateProducts;
import static org.assertj.core.api.Assertions.assertThat;

// output-based test
class RevisitedPriceEngineTest {

    // todo: add @Test
    @Test
    void 최소_할인율을_확인한다() {
        // todo: arrange
        //  1. create List<Product> fixture
        var products = List.<Product>of();

        // todo: act
        //  1. calculate discount rate
        var result = PriceEngine.calculateDiscount(products);

        // todo: assert
        //  1. assert minimum discount rate
        assertThat(result).isEqualTo(0);
    }

    public void 최대_할인율을_확인한다() {
        // todo: arrange
        //  1. implement builder
        var products = generateProducts(51);

        // todo: act
        //  1. calculate discount rate
        var result = PriceEngine.calculateDiscount(products);

        // todo: assert
        //  1. assert maximum discount rate
        assertThat(result).isEqualTo(PriceEngine.MAX_DISCOUNT);
    }
}
