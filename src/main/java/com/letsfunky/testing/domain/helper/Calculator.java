package com.letsfunky.testing.domain.helper;

public class Calculator {

    public static int sum(int x, int y) {
        return x + y;
    }

    public static String invert(String s) {
        try {
            var d = Double.parseDouble(s);
            return String.valueOf(1.0 / d);
        } catch (Exception e) {
            return new StringBuilder(s).reverse().toString();
        }
    }
}
