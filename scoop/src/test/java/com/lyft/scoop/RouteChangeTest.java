package com.lyft.scoop;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class RouteChangeTest {
    private static final TransitionDirection ENTER_TRANSITION = TransitionDirection.ENTER;
    private static final Scoop TEST_SCOOP = new Scoop.Builder(null, null).build();

    @Test
    public void screenEmptyPath() {
        List<Screen> fromPath = Arrays.<Screen>asList();
        List<Screen> toPath = Arrays.<Screen>asList();

        RouteChange routeChange = new RouteChange(fromPath, toPath, ENTER_TRANSITION);
        ScreenSwap screenSwap = routeChange.toScreenSwap(TEST_SCOOP);

        assertNull(screenSwap.next);
        assertNull(screenSwap.previous);
        assertEquals(TEST_SCOOP, screenSwap.scoop);
        assertEquals(ENTER_TRANSITION, screenSwap.direction);
    }

    @Test
    public void screenOneElementPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        RouteChange routeChange = new RouteChange(fromPath, toPath, ENTER_TRANSITION);
        ScreenSwap screenSwap = routeChange.toScreenSwap(TEST_SCOOP);

        assertEquals(new ScreenA(), screenSwap.next);
        assertEquals(new ScreenA(), screenSwap.previous);
        assertEquals(TEST_SCOOP, screenSwap.scoop);
        assertEquals(ENTER_TRANSITION, screenSwap.direction);
    }

    @Test
    public void screenMultipleElementPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());

        RouteChange routeChange = new RouteChange(fromPath, toPath, ENTER_TRANSITION);
        ScreenSwap screenSwap = routeChange.toScreenSwap(TEST_SCOOP);

        assertEquals(new ScreenB(), screenSwap.next);
        assertEquals(new ScreenB(), screenSwap.previous);
        assertEquals(TEST_SCOOP, screenSwap.scoop);
        assertEquals(ENTER_TRANSITION, screenSwap.direction);
    }

    static class ScreenA extends Screen {
    }

    static class ScreenB extends Screen {
    }
}
