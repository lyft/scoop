package com.lyft.scoop;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class RouterTest {

    private TestRouter router;

    @Before
    public void setUp() {
        router = new TestRouter();
    }

    @Test
    public void goTo() {

        Screen screen1 = Screen.create(ViewController1.class);

        router.goTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(null, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.ENTER, router.lastScreenChange.direction);
    }

    @Test
    public void goToSameController() {

        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController1.class);

        router.goTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.goTo(screen2);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
    }

    @Test
    public void goBack() {
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController2.class);

        router.goTo(screen1);
        router.goTo(screen2);
        router.goBack();

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(screen2, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);
    }

    @Test
    public void resetToExisting() {
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController2.class);

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
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController2.class);

        router.goTo(screen2);
        router.resetTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(screen2, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void replaceWith() {
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController2.class);

        router.goTo(screen1);
        router.replaceWith(screen2);

        Assert.assertEquals(screen2, router.lastScreenChange.next);
        Assert.assertEquals(screen1, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.ENTER, router.lastScreenChange.direction);

        checkIfRouterBackstackIsEmpty();
    }

    @Test
    public void replaceAllWith() {
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController2.class);

        router.goTo(screen1);
        router.replaceAllWith(Arrays.asList(screen1, screen2));
        Assert.assertEquals(screen2, router.lastScreenChange.next);
    }

    @Test
    public void replaceToSameController() {

        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController1.class);

        router.replaceWith(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.replaceWith(screen2);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
    }

    private void checkIfRouterBackstackIsEmpty() {Assert.assertEquals(false, router.goBack());}


    @Test
    public void sameController() {

        Screen previous = Screen.create(ViewController1.class);
        Screen next = Screen.create(ViewController1.class);
        Assert.assertTrue(Router.sameController(previous, next));
    }

    @Test
    public void differentController() {

        Screen previous = Screen.create(ViewController1.class);
        Screen next = Screen.create(ViewController2.class);
        Assert.assertFalse(Router.sameController(previous, next));
    }

    static class ViewController1 extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    static class ViewController2 extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    static class TestRouter extends Router {

        private RouteChange lastScreenChange;

        public TestRouter() {
            super(new ScreenScooper());
        }

        @Override
        protected void onScoopChanged(RouteChange routeChange) {
            lastScreenChange = routeChange;
        }
    }
}
