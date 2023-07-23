package com.letsfunky.testingstyle;

import java.util.List;

public class PriceEngine {

    public  static final double MAX_DISCOUNT = 0.5;

    public static double calculateDiscount(List<Product> products) {
        var discount = products.size() * 0.01;
        return Math.min(discount, MAX_DISCOUNT);
    }
}
