package com.lyft.scoop;

import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
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

        Assert.assertEquals(screen1, defaultRouter.lastScreenChange.next);
        Assert.assertEquals(screen2, defaultRouter.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, defaultRouter.lastScreenChange.direction);
    }

    @Test
    public void goTo() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.goTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(null, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.ENTER, router.lastScreenChange.direction);
    }

    @Test
    public void goToSameController() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen1();

        router.goTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.goTo(screen2);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
    }

    @Test
    public void goBack() {

        router = new TestRouter(false);

        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.goTo(screen2);
        Assert.assertTrue(router.goBack());

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(screen2, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);
    }

    @Test
    public void goBackAllowEmptyStack() {

        router = new TestRouter(true);

        Screen screen1 = new Screen1();

        router.goTo(screen1);
        Assert.assertTrue(router.goBack());

        Assert.assertNull(router.lastScreenChange.next);
        Assert.assertEquals(screen1, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);
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

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(screen2, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void resetToNew() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen2);
        router.resetTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(screen2, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void replaceWith() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.replaceWith(screen2);

        Assert.assertEquals(screen2, router.lastScreenChange.next);
        Assert.assertEquals(screen1, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.ENTER, router.lastScreenChange.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void replaceAllWith() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen2();

        router.goTo(screen1);
        router.replaceAllWith(screen1, screen2);
        Assert.assertEquals(screen2, router.lastScreenChange.next);
    }

    @Test
    public void emptyBackstackGoTo() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.goTo(screen1);
        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.goBack();
    }

    @Test
    public void emptyBackstackReplaceWith() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.replaceWith(screen1);
        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.goBack();
    }

    @Test
    public void emptyBackstackResetTo() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();

        router.resetTo(screen1);
        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.goBack();
    }

    @Test
    public void replaceToSameController() {

        router = new TestRouter(false);
        Screen screen1 = new Screen1();
        Screen screen2 = new Screen1();

        router.replaceWith(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.replaceWith(screen2);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
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
    public void replaceAllWithEmptyList() {


        router = new TestRouter(false);

        router.replaceAllWith(Collections.EMPTY_LIST);
        Assert.assertFalse(router.hasActiveScreen());
    }

    private void checkIfRouterBackstackIsEmpty() {Assert.assertEquals(false, router.goBack());}


    @Test
    public void sameController() {

        Screen previous = new Screen1();
        Screen next = new Screen1();
        Assert.assertTrue(Router.sameScreen(previous, next));
    }

    @Test
    public void differentController() {

        Screen previous = new Screen1();
        Screen next = new Screen2();
        Assert.assertFalse(Router.sameScreen(previous, next));
    }

    @Layout(0)
    static class Screen1 extends Screen {
    }

    @Layout(0)
    static class Screen2 extends Screen {
    }

    static class TestRouter extends Router {

        private RouteChange lastScreenChange;

        public TestRouter(boolean allowEmptyStack) {
            super(new ScreenScooper(), allowEmptyStack);
        }

        @Override
        protected void onScoopChanged(RouteChange routeChange) {
            lastScreenChange = routeChange;
        }
    }

    static class TestDefaultRouter extends Router {

        private RouteChange lastScreenChange;

        public TestDefaultRouter() {
            super(new ScreenScooper());
        }

        @Override
        protected void onScoopChanged(RouteChange routeChange) {
            lastScreenChange = routeChange;
        }
    }
}
