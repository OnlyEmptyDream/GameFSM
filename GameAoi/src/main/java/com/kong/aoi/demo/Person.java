package com.kong.aoi.demo;

public class Person {
    private static Person INSTANCE = new Person();

    private Person() {

    }

    public static Person getInstance() {
        return INSTANCE;
    }
}
