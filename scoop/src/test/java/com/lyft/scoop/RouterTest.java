package com.lyft.scoop;

import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class RouterTest {

    private TestRouter router;

    @Test
    public void defaultRouter() {

        TestDefaultRouter defaultRouter = new TestDefaultRouter();

        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        defaultRouter.goTo(screen1);
        defaultRouter.goTo(screen2);
        Assert.assertTrue(defaultRouter.goBack());

        Assert.assertEquals(screen1, defaultRouter.fromPath.get(0));
        Assert.assertEquals(screen2, defaultRouter.fromPath.get(1));

        Assert.assertEquals(screen1, defaultRouter.toPath.get(0));
        Assert.assertEquals(TransitionDirection.EXIT, defaultRouter.direction);
    }

    @Test
    public void goTo() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.goTo(screen1);

        Assert.assertTrue(router.fromPath.isEmpty());
        Assert.assertEquals(screen1, router.toPath.get(0));
        Assert.assertEquals(TransitionDirection.ENTER, router.direction);
    }

    @Test
    public void goToSameController() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen1();

        router.goTo(screen1);

        Assert.assertEquals(screen1, router.toPath.get(0));

        router.goTo(screen2);

        Assert.assertEquals(screen1, router.toPath.get(0));
    }

    @Test
    public void goBack() {

        router = new TestRouter(false);

        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.goTo(screen2);
        Assert.assertTrue(router.goBack());

        Assert.assertEquals(screen1, router.fromPath.get(0));
        Assert.assertEquals(screen2, router.fromPath.get(1));

        Assert.assertEquals(screen1, router.toPath.get(0));
        Assert.assertEquals(TransitionDirection.EXIT, router.direction);
    }

    @Test
    public void goBackAllowEmptyStack() {

        router = new TestRouter(true);

        Screen screen1 = new Screen1();

        router.goTo(screen1);

        Assert.assertTrue(router.goBack());

        Assert.assertEquals(screen1, router.fromPath.get(0));
        Assert.assertTrue(router.toPath.isEmpty());
        Assert.assertEquals(TransitionDirection.EXIT, router.direction);
    }

    @Test
    public void goBackNoScreens() {

        router = new TestRouter(true);
        Assert.assertFalse(router.goBack());
    }

    @Test
    public void resetToExisting() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.goTo(screen2);
        router.resetTo(screen1);

        Assert.assertEquals(screen1, router.fromPath.get(0));
        Assert.assertEquals(screen2, router.fromPath.get(1));
        Assert.assertEquals(screen1, router.toPath.get(0));

        Assert.assertEquals(TransitionDirection.EXIT, router.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void resetToNew() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen2);
        router.resetTo(screen1);

        Assert.assertEquals(screen2, router.fromPath.get(0));
        Assert.assertEquals(screen1, router.toPath.get(0));
        Assert.assertEquals(TransitionDirection.EXIT, router.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void replaceWith() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.replaceWith(screen2);

        Assert.assertEquals(screen1, router.fromPath.get(0));
        Assert.assertEquals(screen2, router.toPath.get(0));
        Assert.assertEquals(TransitionDirection.ENTER, router.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void replaceAllWith() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.replaceAllWith(screen1, screen2);

        Assert.assertEquals(screen1, router.fromPath.get(0));
        Assert.assertEquals(screen1, router.toPath.get(0));
        Assert.assertEquals(screen2, router.toPath.get(1));
    }

    @Test
    public void emptyBackstackGoTo() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.goTo(screen1);

        Assert.assertTrue(router.fromPath.isEmpty());
        Assert.assertEquals(screen1, router.toPath.get(0));
    }

    @Test
    public void emptyBackstackReplaceWith() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.replaceWith(screen1);
        Assert.assertTrue(router.fromPath.isEmpty());
        Assert.assertEquals(screen1, router.toPath.get(0));
    }

    @Test
    public void emptyBackstackResetTo() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.resetTo(screen1);
        Assert.assertTrue(router.fromPath.isEmpty());
        Assert.assertEquals(screen1, router.toPath.get(0));
    }

    @Test
    public void replaceToSameScreen() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen1();

        router.replaceWith(screen1);
        router.replaceWith(screen2);

        Assert.assertTrue(router.fromPath.isEmpty());
        Assert.assertEquals(screen1, router.toPath.get(0));
    }

    @Test
    public void hasActiveScreen() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.goTo(screen1);
        Assert.assertTrue(router.hasActiveScreen());

        router.goBack();
        Assert.assertFalse(router.hasActiveScreen());
    }

    @Test
    public void replaceAllWithEmptyListOnDisallowedEmptyStack() {

        router = new TestRouter(true);

        router.replaceAllWith(Collections.<Screen>emptyList());
        Assert.assertFalse(router.hasActiveScreen());
    }

    // Feel like this naming no longer valid. We are not checking controllers anymore...
    @Test
    public void sameController() {

        Screen previous = new Screen1();
        Screen next = new Screen1();
        Assert.assertTrue(Router.sameScreen(previous, next));
    }

    // Feel like this naming no longer valid. We are not checking controllers anymore...
    @Test
    public void differentController() {

        Screen previous = new Screen1();
        Screen next = new Screen2();
        Assert.assertFalse(Router.sameScreen(previous, next));
    }

    private void checkIfRouterBackstackIsEmpty() {
        Assert.assertEquals(false, router.goBack());
    }

    @Layout(0)
    static class Screen1 extends Screen {
    }

    @Layout(0)
    static class Screen2 extends Screen {
    }

    static class TestRouter extends Router {

        List<Screen> fromPath;
        List<Screen> toPath;
        TransitionDirection direction;

        public TestRouter(boolean allowEmptyStack) {
            super(allowEmptyStack);
        }

        @Override
        protected void onScoopChanged(RouteChange routeChange) {

        }

        @Override
        protected void onScreenChanged(List<Screen> fromPath, List<Screen> toPath, TransitionDirection direction) {
            this.fromPath = fromPath;
            this.toPath = toPath;
            this.direction = direction;
        }
    }

    // Why do we need this guy???!!!
    static class TestDefaultRouter extends Router {

        List<Screen> fromPath;
        List<Screen> toPath;
        TransitionDirection direction;

        public TestDefaultRouter() {

            super();
        }

        @Override
        protected void onScoopChanged(RouteChange routeChange) {

        }

        @Override
        protected void onScreenChanged(List<Screen> fromPath, List<Screen> toPath, TransitionDirection direction) {
            this.fromPath = fromPath;
            this.toPath = toPath;
            this.direction = direction;
        }
    }
}
