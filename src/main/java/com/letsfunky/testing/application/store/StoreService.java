package com.letsfunky.testing.application.store;

import org.springframework.stereotype.Service;

@Service
public class StoreService {

    public boolean hasInventory(String goods, int count) {
        // NOTE: dummy return for test purpose
        return true;
    }

    public int removeInventory(String goods, int count) {
        // NOTE: dummy return for test purpose
        return 123;
    }

}
