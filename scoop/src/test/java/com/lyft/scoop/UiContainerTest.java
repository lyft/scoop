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
        final ScreenSwap screenSwap = createRouteChange(new ViewControllerWithTransitions(), new ViewControllerWithTransitions());
        Assert.assertNotNull(UiContainer.getEnterTransition(screenSwap));
        Assert.assertNotNull(UiContainer.getExitTransition(screenSwap));
    }

    @Test
    public void enterTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final ScreenSwap screenSwap = createRouteChange(new ViewControllerWithTransitionWithoutDefaultConstructor(), new ViewControllerWithTransitionWithoutDefaultConstructor());
        UiContainer.getEnterTransition(screenSwap);
    }

    @Test
    public void exitTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final ScreenSwap screenSwap = createRouteChange(new ViewControllerWithTransitionWithoutDefaultConstructor(), new ViewControllerWithTransitionWithoutDefaultConstructor());
        UiContainer.getExitTransition(screenSwap);
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        final ScreenSwap screenSwap = createRouteChange(new ViewControllerWithoutTransitions(), new ViewControllerWithoutTransitions());
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getEnterTransition(screenSwap).getClass());
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getExitTransition(screenSwap).getClass());
    }

    private ScreenSwap createRouteChange(final Screen previous, final Screen next) {
        return new ScreenSwap(null, previous, next, null);
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
