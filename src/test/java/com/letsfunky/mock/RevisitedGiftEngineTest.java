package com.letsfunky.mock;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static com.letsfunky.mock.GiftEngine.GIFT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class RevisitedGiftEngineTest {

    // examine spy
    // `Stub` gives out data that goes to the SUT
    @Test
    void 무료선물이_추가된다() {
        // todo: arrange
        //  1. create spy instance of HashMap<String, Integer> productPrices
        //  2. create GiftEngine
        var spyProductPrices = spy(new HashMap<String, Integer>());
        var giftEngine = new GiftEngine();

        // todo: act
        //  1. add gift
        giftEngine.addGift(spyProductPrices);


        // todo: assert
        //  1. verify GiftEngine.GIFT has been added with price 0 to productPrices
        verify(spyProductPrices).put(GIFT, 0);
        assertThat(spyProductPrices.size()).isEqualTo(1);
    }
}