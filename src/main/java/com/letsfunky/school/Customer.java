package com.letsfunky.school;

public class Customer {

    public boolean purchase(Store store, String product, int count) {
        return store.sell(product, count);
    }
}
