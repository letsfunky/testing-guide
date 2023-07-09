package com.letsfunky.school;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private final Map<String, Integer> inventory = new HashMap<>();

    public void addInventory(String product, int count) {
        inventory.put(product, count);
    }

    public boolean sell(String product, int count) {
        if (hasInventory(product, count)) {
            removeInventory(product, count);
            return true;
        }

        return false;
    }

    public boolean hasInventory(String product, int count) {
        return inventory.getOrDefault(product, 0) >= count;
    }

    private void removeInventory(String product, int count) {
        inventory.computeIfPresent(product, (String key, Integer value) -> value - count);
    }
}
