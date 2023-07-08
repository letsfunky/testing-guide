package com.letsfunky.mock;

import java.util.Map;

public class GiftEngine {

    public GiftEngine() {
    }

    public void addGift(Map<String, Integer> productPrices) {
        productPrices.put("free-snack", 0);
    }
}
