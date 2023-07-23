package com.letsfunky.mock;

import java.util.Map;

public class GiftEngine {
    public static final String GIFT = "free-snack";

    public void addGift(Map<String, Integer> productPrices) {
        productPrices.put(GIFT, 0);
    }
}
