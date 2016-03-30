package com.lyft.scoop;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouterTest {

    private TestRouter router;

    @Test
    public void defaultRouter() {

        TestRouter defaultRouter = new TestRouter();

        Screen screenA = new ScreenA();
        Screen screenB = new ScreenB();

        defaultRouter.goTo(screenA);
        defaultRouter.goTo(screenB);
        assertTrue(defaultRouter.goBack());

        assertEquals(screenA, defaultRouter.fromPath.get(0));
        assertEquals(screenB, defaultRouter.fromPath.get(1));

        assertEquals(screenA, defaultRouter.toPath.get(0));
        assertEquals(TransitionDirection.EXIT, defaultRouter.direction);
    }

    @Test
    public void goTo() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();

        router.goTo(screenA);

        assertTrue(router.fromPath.isEmpty());
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(TransitionDirection.ENTER, router.direction);
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void goToSameScreen() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();
        Screen screenB = new ScreenA();

        router.goTo(screenA);

        assertEquals(screenA, router.toPath.get(0));

        router.goTo(screenB);

        assertEquals(screenA, router.toPath.get(0));
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void goBack() {

        router = new TestRouter(false);

        Screen screenA = new ScreenA();
        Screen screenB = new ScreenB();

        router.goTo(screenA);
        router.goTo(screenB);
        assertTrue(router.goBack());

        assertEquals(screenA, router.fromPath.get(0));
        assertEquals(screenB, router.fromPath.get(1));

        assertEquals(screenA, router.toPath.get(0));
        assertEquals(TransitionDirection.EXIT, router.direction);
        assertEquals(3, router.routeChangeCount);
    }

    @Test
    public void goBackAllowEmptyStack() {

        router = new TestRouter(true);

        Screen screenA = new ScreenA();

        router.goTo(screenA);

        assertTrue(router.goBack());

        assertEquals(screenA, router.fromPath.get(0));
        assertTrue(router.toPath.isEmpty());
        assertEquals(TransitionDirection.EXIT, router.direction);
        assertEquals(2, router.routeChangeCount);
    }

    @Test
    public void goBackNoScreens() {

        router = new TestRouter(true);
        assertFalse(router.goBack());
        assertEquals(0, router.routeChangeCount);
    }

    @Test
    public void resetToExisting() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();
        Screen screenB = new ScreenB();

        router.goTo(screenA);
        router.goTo(screenB);
        router.resetTo(screenA);

        assertEquals(screenA, router.fromPath.get(0));
        assertEquals(screenB, router.fromPath.get(1));
        assertEquals(screenA, router.toPath.get(0));

        assertEquals(TransitionDirection.EXIT, router.direction);

        checkIfRouterBackstackIsEmpty();
        assertEquals(4, router.routeChangeCount);
    }

    @Test
    public void resetToNew() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();
        Screen screenB = new ScreenB();

        router.goTo(screenB);
        router.resetTo(screenA);

        assertEquals(screenB, router.fromPath.get(0));
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(TransitionDirection.EXIT, router.direction);
        checkIfRouterBackstackIsEmpty();
        assertEquals(3, router.routeChangeCount);
    }

    @Test
    public void replaceWith() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();
        Screen screenB = new ScreenB();

        router.goTo(screenA);
        router.replaceWith(screenB);

        assertEquals(screenA, router.fromPath.get(0));
        assertEquals(screenB, router.toPath.get(0));
        assertEquals(TransitionDirection.ENTER, router.direction);

        checkIfRouterBackstackIsEmpty();
        assertEquals(3, router.routeChangeCount);
    }

    @Test
    public void replaceAllWith() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();
        Screen screenB = new ScreenB();

        router.goTo(screenA);
        router.replaceAllWith(screenA, screenB);

        assertEquals(screenA, router.fromPath.get(0));
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(screenB, router.toPath.get(1));
        assertEquals(2, router.routeChangeCount);
    }

    @Test
    public void emptyBackstackGoTo() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();

        router.goTo(screenA);

        assertTrue(router.fromPath.isEmpty());
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void emptyBackstackReplaceWith() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();

        router.replaceWith(screenA);
        assertTrue(router.fromPath.isEmpty());
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void emptyBackstackResetTo() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();

        router.resetTo(screenA);
        assertTrue(router.fromPath.isEmpty());
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void replaceToSameScreen() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();
        Screen screenB = new ScreenA();

        router.replaceWith(screenA);
        router.replaceWith(screenB);

        assertTrue(router.fromPath.isEmpty());
        assertEquals(screenA, router.toPath.get(0));
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void hasActiveScreen() {

        router = new TestRouter(false);
        Screen screenA = new ScreenA();

        router.goTo(screenA);
        assertTrue(router.hasActiveScreen());

        router.goBack();
        assertFalse(router.hasActiveScreen());
    }

    @Test
    public void replaceAllWithEmptyListOnDisallowedEmptyStack() {

        router = new TestRouter(true);

        router.replaceAllWith(Collections.<Screen>emptyList());
        assertFalse(router.hasActiveScreen());
        assertEquals(1, router.routeChangeCount);
    }

    @Test
    public void sameScreen() {

        Screen previous = new ScreenA();
        Screen next = new ScreenA();
        assertTrue(Router.sameScreen(previous, next));
    }

    @Test
    public void differentScreen() {

        Screen previous = new ScreenA();
        Screen next = new ScreenB();
        assertFalse(Router.sameScreen(previous, next));
    }

    private void checkIfRouterBackstackIsEmpty() {
        assertEquals(false, router.goBack());
    }

    static class ScreenA extends Screen {
    }

    static class ScreenB extends Screen {
    }

    static class TestRouter extends Router {

        List<Screen> fromPath;
        List<Screen> toPath;
        TransitionDirection direction;
        int routeChangeCount;

        public TestRouter(boolean allowEmptyStack) {
            super(allowEmptyStack);
        }

        public TestRouter() {
            super();
        }

        @Override
        protected void onRouteChanged(RouteChange routeChange) {
            routeChangeCount++;
            fromPath = routeChange.fromPath;
            toPath = routeChange.toPath;
            direction = routeChange.direction;
        }
    }
}
