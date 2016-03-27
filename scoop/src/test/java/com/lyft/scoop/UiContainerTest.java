package com.lyft.scoop;

import com.lyft.scoop.transitions.EnterTransition;
import com.lyft.scoop.transitions.ExitTransition;
import com.lyft.scoop.transitions.basic.BackwardSlideTransition;
import com.lyft.scoop.transitions.basic.ForwardSlideTransition;
import com.lyft.scoop.transitions.basic.HorizontalSlideTransition;
import com.lyft.scoop.transitions.basic.InstantTransition;
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
        final RouteChange routeChange = createRouteChange(new ViewControllerWithTransitions(), new ViewControllerWithTransitions());
        Assert.assertNotNull(UiContainer.getEnterTransition(routeChange));
        Assert.assertNotNull(UiContainer.getExitTransition(routeChange));
    }

    @Test
    public void enterTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final RouteChange routeChange = createRouteChange(new ViewControllerWithTransitionWithoutDefaultConstructor(), new ViewControllerWithTransitionWithoutDefaultConstructor());
        UiContainer.getEnterTransition(routeChange);
    }

    @Test
    public void exitTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final RouteChange routeChange = createRouteChange(new ViewControllerWithTransitionWithoutDefaultConstructor(), new ViewControllerWithTransitionWithoutDefaultConstructor());
        UiContainer.getExitTransition(routeChange);
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        final RouteChange routeChange = createRouteChange(new ViewControllerWithoutTransitions(), new ViewControllerWithoutTransitions());
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getEnterTransition(routeChange).getClass());
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getExitTransition(routeChange).getClass());
    }

    private RouteChange createRouteChange(final Screen previous, final Screen next) {
        return new RouteChange(null, previous, next, null);
    }

    @EnterTransition(ForwardSlideTransition.class)
    @ExitTransition(BackwardSlideTransition.class)
    @Layout(0)
    static class ViewControllerWithTransitions extends Screen {
    }

    @EnterTransition(HorizontalSlideTransition.class)
    @ExitTransition(HorizontalSlideTransition.class)
    @Layout(0)
    static class ViewControllerWithTransitionWithoutDefaultConstructor extends Screen {
    }

    @Layout(0)
    static class ViewControllerWithoutTransitions extends Screen {
    }
}
