package com.example.scoop.basics.common;

public class Objects {

    public static <T> T firstNonNull(T item1, T item2) {
        if (item1 != null) {
            return item1;
        } else if (item2 == null) {
            throw new NullPointerException("All items are null");
        }
        return item2;
    }

    public static <T> T firstNonNull(T... items) {
        for (T item : items) {
            if (item != null) {
                return item;
            }
        }

        throw new NullPointerException("All items are null");
    }

    public static <T> boolean equals(T first, T second) {
        return first == null ? first == second : first.equals(second);
    }
}
