package com.lyft.scoop;

import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
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
    public void getTransitionsFromViewController() {
        final ViewControllerWithTransitions viewControllerWithTransitions = new ViewControllerWithTransitions();
        final ScreenTransition enterTransition =
                UiContainer.getTransition(viewControllerWithTransitions, TransitionDirection.ENTER);
        final ScreenTransition exitTransition =
                UiContainer.getTransition(viewControllerWithTransitions, TransitionDirection.EXIT);

        Assert.assertEquals(enterTransition.getClass(), ForwardSlideTransition.class);
        Assert.assertEquals(exitTransition.getClass(), BackwardSlideTransition.class);
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        final ViewController viewControllerWithoutTransitions = new ViewControllerWithoutTransitions();

        final ScreenTransition enterTransition =
                UiContainer.getTransition(viewControllerWithoutTransitions, TransitionDirection.ENTER);
        final ScreenTransition exitTransition =
                UiContainer.getTransition(viewControllerWithoutTransitions, TransitionDirection.EXIT);

        Assert.assertEquals(enterTransition.getClass(), InstantTransition.class);
        Assert.assertEquals(exitTransition.getClass(), InstantTransition.class);
    }

    private static class ViewControllerWithTransitions extends ViewController {

        @Override
        protected ScreenTransition enterTransition() {
            return new ForwardSlideTransition();
        }

        @Override
        protected ScreenTransition exitTransition() {
            return new BackwardSlideTransition();
        }

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    private static class ViewControllerWithoutTransitions extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
