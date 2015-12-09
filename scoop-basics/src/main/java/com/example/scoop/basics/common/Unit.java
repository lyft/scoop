package com.example.scoop.basics.common;

public final class Unit {

    private final static Unit INSTANCE = new Unit();

    private Unit() {
    }

    public static Unit create() {
        return INSTANCE;
    }
}