package com.lyft.scoop;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScreenBackstackTest {

    private ScreenBackstack backStack;

    @Before
    public void setUp() {
        backStack = new ScreenBackstack();
    }

    @Test
    public void asList() {
        Screen1 screen1 = new Screen1();
        Screen2 screen2 = new Screen2();
        backStack.push(screen1);
        backStack.push(screen2);

        List<Screen> list = backStack.asList();

        assertEquals(screen1, list.get(0));
        assertEquals(screen2, list.get(1));
    }

    @Test
    public void asListWithEmptyBackstack() {
        List<Screen> list = backStack.asList();

        assertTrue(list.isEmpty());
    }

    static class Screen1 extends Screen {

    }

    static class Screen2 extends Screen {

    }
}
