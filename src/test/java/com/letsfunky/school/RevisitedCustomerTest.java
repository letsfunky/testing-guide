package com.letsfunky.school;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RevisitedCustomerTest {

    @Nested
    class ClassicalSchool {

        // todo: add classical school test
        @Test
        void 구매가_성공하면_재고가_줄어든다() {
            // todo: arrange
            //  1. create Store and add inventory
            //  2. create Customer
            var store = new Store();
            var product = "shampoo";
            store.addInventory(product, 3);
            var customer = new Customer();

            // todo: act
            //  1. customer purchases products
            var result = customer.purchase(store, product, 2);

            // todo: assert
            //  1. assert purchase succeeds
            //  2. assert inventory reduced
            assertThat(result).isTrue();
            assertThat(store.hasInventory(product, 1)).isTrue();
        }
    }

    @Nested
    class Mockist {

        // todo: add mockist test
        @Test
        void 구매가_성공하면_재고가_줄어든다2() {
            // todo: arrange
            //  1. create Store mock
            //  2. return true when store.sell(..) invoked
            //  3. create Customer
            var product = "shampoo";
            var store = mock(Store.class);
            when(store.sell(product, 3)).thenReturn(true);
            var customer = new Customer();

            // todo: act
            //  1. customer purchases products
            var result = customer.purchase(store, product, 3);

            // todo: assert
            //  1. assert purchase succeeds
            //  2. assert product has removed from store inventory (cannot assert)
            assertThat(result).isTrue();
            assertThat(store.hasInventory(product, 2)).isFalse(); // false positive
            assertThat(store.hasInventory(product, 1)).isTrue();
        }
    }
}