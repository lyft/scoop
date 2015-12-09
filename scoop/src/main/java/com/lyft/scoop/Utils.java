package com.lyft.scoop;

import java.lang.annotation.Annotation;

final class Utils {

    public static <T extends Annotation> boolean hasAnnotation(Class<?> clazz, Class<T> annotationClass) {
        return clazz.getAnnotation(annotationClass) != null;
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T checkNotNull(T reference, String errorMessage, Object... args) {
        if (reference == null) {
            throw new NullPointerException(String.format(errorMessage, args));
        }
        return reference;
    }
}
