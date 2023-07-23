package com.letsfunky.testingstyle;

import java.util.List;
import java.util.stream.IntStream;

public class RevisitedProductBuilder {

    public static Product dummyProduct() {
        return new Product("dummy");
    }

    public static List<Product> generateProducts(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> new Product("product-" + i))
            .toList();
    }
}
