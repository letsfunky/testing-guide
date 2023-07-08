package com.letsfunky.mock;

public class Car {

    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void startEngine() {
        engine.selfCheck();
    }
}
