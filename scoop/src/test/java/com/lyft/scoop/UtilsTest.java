package com.lyft.scoop;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

    @Test
    public void hasAnnotation() {
        Assert.assertTrue(Utils.hasAnnotation(ClassWithAnnotation.class, Controller.class));
    }

    @Test
    public void doesNotHaveAnnotation() {
        Assert.assertFalse(Utils.hasAnnotation(ClassWithoutAnnotation.class, Controller.class));
    }

    @Controller(ViewController.class)
    static class ClassWithAnnotation {

    }

    static class ClassWithoutAnnotation {

    }
}
