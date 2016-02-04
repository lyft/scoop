package com.lyft.scoop;

import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import com.lyft.scoop.transitions.HorizontalSlideTransition;
import com.lyft.scoop.transitions.InstantTransition;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class UiContainerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getTransitions() {
        final RouteChange routeChange = createRouteChange(ViewControllerWithTransitions.class);

        Assert.assertNotNull(UiContainer.getEnterTransition(routeChange));
        Assert.assertNotNull(UiContainer.getExitTransition(routeChange));
    }

    @Test
    public void enterTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final RouteChange routeChange = createRouteChange(ViewControllerWithTransitionWithoutDefaultConstructor.class);
        UiContainer.getEnterTransition(routeChange);
    }

    @Test
    public void exitTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final RouteChange routeChange = createRouteChange(ViewControllerWithTransitionWithoutDefaultConstructor.class);
        UiContainer.getExitTransition(routeChange);
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        final RouteChange routeChange = createRouteChange(ViewControllerWithoutTransitions.class);
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getEnterTransition(routeChange).getClass());
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getExitTransition(routeChange).getClass());
    }

    private RouteChange createRouteChange(final Class<? extends ViewController> viewController) {
        final Screen previous = Screen.create(viewController);
        final Screen next = Screen.create(viewController);
        return new RouteChange(null, previous, next, null);
    }

    @EnterTransition(ForwardSlideTransition.class)
    @ExitTransition(BackwardSlideTransition.class)
    static class ViewControllerWithTransitions extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    @EnterTransition(HorizontalSlideTransition.class)
    @ExitTransition(HorizontalSlideTransition.class)
    static class ViewControllerWithTransitionWithoutDefaultConstructor extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    static class ViewControllerWithoutTransitions extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
