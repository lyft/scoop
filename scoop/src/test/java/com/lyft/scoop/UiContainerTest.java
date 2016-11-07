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
    public void getTransitionsFromViewController() {
        final ViewControllerWithTransitions viewControllerWithTransitions = new ViewControllerWithTransitions();
        final Class<? extends ScreenTransition> enterTransition =
                UiContainer.getTransition(viewControllerWithTransitions, TransitionDirection.ENTER).getClass();
        final Class<? extends ScreenTransition> exitTransition =
                UiContainer.getTransition(viewControllerWithTransitions, TransitionDirection.EXIT).getClass();

        Assert.assertEquals(enterTransition, ForwardSlideTransition.class);
        Assert.assertEquals(exitTransition, BackwardSlideTransition.class);
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        final ViewController viewControllerWithoutTransitions = new ViewControllerWithoutTransitions();

        final Class<? extends ScreenTransition> enterTransition =
                UiContainer.getTransition(viewControllerWithoutTransitions, TransitionDirection.ENTER).getClass();
        final Class<? extends ScreenTransition> exitTransition =
                UiContainer.getTransition(viewControllerWithoutTransitions, TransitionDirection.EXIT).getClass();

        Assert.assertEquals(enterTransition, InstantTransition.class);
        Assert.assertEquals(exitTransition, InstantTransition.class);
    }

    @Test
    public void enterTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        UiContainer.getTransition(new ViewControllerWithInvalidTransitions(), TransitionDirection.ENTER);
    }

    @Test
    public void exitTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        UiContainer.getTransition(new ViewControllerWithInvalidTransitions(), TransitionDirection.EXIT);
    }

    private static class ViewControllerWithTransitions extends ViewController {

        @Override
        protected Class<? extends ScreenTransition> enterTransition() {
            return ForwardSlideTransition.class;
        }

        @Override
        protected Class<? extends ScreenTransition> exitTransition() {
            return BackwardSlideTransition.class;
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

    private static class ViewControllerWithInvalidTransitions extends ViewController {

        @Override
        protected Class<? extends ScreenTransition> enterTransition() {
            return HorizontalSlideTransition.class;
        }

        @Override
        protected Class<? extends ScreenTransition> exitTransition() {
            return HorizontalSlideTransition.class;
        }

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
