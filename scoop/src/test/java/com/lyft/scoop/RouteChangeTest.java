package com.lyft.scoop;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class RouteChangeTest {

    private static final TransitionDirection ENTER_TRANSITION = TransitionDirection.ENTER;

    @Test
    public void screenEmptyPath() {
        List<Screen> fromPath = Arrays.<Screen>asList();
        List<Screen> toPath = Arrays.<Screen>asList();

        RouteChange routeChange = new RouteChange(fromPath, toPath, ENTER_TRANSITION);
        ScreenSwap screenSwap = routeChange.toScreenSwap();

        assertNull(screenSwap.next);
        assertNull(screenSwap.previous);
        assertEquals(ENTER_TRANSITION, screenSwap.direction);
    }

    @Test
    public void screenOneElementPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        RouteChange routeChange = new RouteChange(fromPath, toPath, ENTER_TRANSITION);
        ScreenSwap screenSwap = routeChange.toScreenSwap();

        assertEquals(new ScreenA(), screenSwap.next);
        assertEquals(new ScreenA(), screenSwap.previous);
        assertEquals(ENTER_TRANSITION, screenSwap.direction);
    }

    @Test
    public void screenMultipleElementPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());

        RouteChange routeChange = new RouteChange(fromPath, toPath, ENTER_TRANSITION);
        ScreenSwap screenSwap = routeChange.toScreenSwap();

        assertEquals(new ScreenB(), screenSwap.next);
        assertEquals(new ScreenB(), screenSwap.previous);
        assertEquals(ENTER_TRANSITION, screenSwap.direction);
    }

    static class ScreenA extends Screen {
    }

    static class ScreenB extends Screen {
    }
}
