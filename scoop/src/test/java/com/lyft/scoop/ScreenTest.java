package com.lyft.scoop;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScreenTest {

    @Test
    public void sameScreen() {

        Screen previous = new ScreenA();
        Screen next = new ScreenA();
        assertTrue(Screen.equals(previous, next));
    }

    @Test
    public void differentScreen() {

        Screen previous = new ScreenA();
        Screen next = new ScreenB();
        assertFalse(Screen.equals(previous, next));
    }

    static class ScreenA extends Screen {
    }

    static class ScreenB extends Screen {
    }
}
