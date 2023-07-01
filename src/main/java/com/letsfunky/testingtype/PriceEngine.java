package com.letsfunky.testingtype;

import java.util.List;

public class PriceEngine {

    public double calculateDiscount(List<Product> products) {
        var discount = products.size() * 0.01;
        return Math.min(discount, 0.5);
    }
}
