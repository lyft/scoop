package com.lyft.scoop;

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
    public void goToSameInstance() {

        Screen screen1 = Screen.create(SingleInstanceViewController.class);
        Screen screen2 = Screen.create(SingleInstanceViewController.class);

        router.goTo(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.goTo(screen2);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
    }

    @Test
    public void goBack() {
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screen2 = Screen.create(ViewController1.class);

        router.goTo(screen1);
        router.goTo(screen2);
        router.goBack();

        Assert.assertEquals(screen1, router.lastScreenChange.next);
        Assert.assertEquals(screen2, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);
    }

    @Test
    public void goUp() {
        Screen screen1 = Screen.create(ViewController1.class);
        Screen screenWithParent = Screen.create(ViewControllerWithParent.class);

        router.goTo(screen1);
        router.goTo(screenWithParent);
        router.goUp();

        Assert.assertEquals(ViewController1.class, router.lastScreenChange.next.getController());
        Assert.assertEquals(screenWithParent, router.lastScreenChange.previous);
        Assert.assertEquals(TransitionDirection.EXIT, router.lastScreenChange.direction);

        checkIfRouterBackstackIsEmpty();
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
    public void replaceToSameInstance() {

        Screen screen1 = Screen.create(SingleInstanceViewController.class);
        Screen screen2 = Screen.create(SingleInstanceViewController.class);

        router.replaceWith(screen1);

        Assert.assertEquals(screen1, router.lastScreenChange.next);

        router.replaceWith(screen2);

        Assert.assertEquals(screen1, router.lastScreenChange.next);
    }

    private void checkIfRouterBackstackIsEmpty() {Assert.assertEquals(false, router.goBack());}

    @Test
    public void sameSingleInstance() {
        Screen previous = Screen.create(SingleInstanceViewController.class);
        Screen next = Screen.create(SingleInstanceViewController.class);
        Assert.assertTrue(Router.sameSingleInstanceController(previous, next));
    }

    @Test
    public void notSameInstance() {

        Screen previous = Screen.create(MultiInstanceViewController.class);
        Screen next = Screen.create(MultiInstanceViewController.class);
        Assert.assertFalse(Router.sameSingleInstanceController(previous, next));
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

    @ParentController(ViewController1.class)
    static class ViewControllerWithParent extends ViewController {

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

    @SingleInstance
    static class SingleInstanceViewController extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    static class MultiInstanceViewController extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
