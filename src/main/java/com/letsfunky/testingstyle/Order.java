package com.letsfunky.testingstyle;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> products() {
        return products;
    }
}
