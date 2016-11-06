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
    public void getTransitionsFromScreen() {
        final ScreenSwap enterScreenSwap =
                new ScreenSwap(new ScreenWithTransitions(), new ScreenWithTransitions(), TransitionDirection.ENTER);
        final ScreenSwap exitScreenSwap =
                new ScreenSwap(new ScreenWithTransitions(), new ScreenWithTransitions(), TransitionDirection.EXIT);

        final Class<? extends ScreenTransition> enterTransition = UiContainer.getTransition(null, enterScreenSwap).getClass();
        final Class<? extends ScreenTransition> exitTransition = UiContainer.getTransition(null, exitScreenSwap).getClass();

        Assert.assertEquals(enterTransition, ForwardSlideTransition.class);
        Assert.assertEquals(exitTransition, BackwardSlideTransition.class);
    }

    @Test
    public void getTransitionsFromViewController() {
        final ScreenSwap enterScreenSwap =
                new ScreenSwap(new ScreenWithoutTransitions(), new ScreenWithoutTransitions(), TransitionDirection.ENTER);
        final ScreenSwap exitScreenSwap =
                new ScreenSwap(new ScreenWithoutTransitions(), new ScreenWithoutTransitions(), TransitionDirection.EXIT);

        final ViewControllerWithTransitions viewControllerWithTransitions = new ViewControllerWithTransitions();
        final Class<? extends ScreenTransition> enterTransition =
                UiContainer.getTransition(viewControllerWithTransitions, enterScreenSwap).getClass();
        final Class<? extends ScreenTransition> exitTransition =
                UiContainer.getTransition(viewControllerWithTransitions, exitScreenSwap).getClass();

        Assert.assertEquals(enterTransition, ForwardSlideTransition.class);
        Assert.assertEquals(exitTransition, BackwardSlideTransition.class);
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        final ScreenSwap enterScreenSwap =
                new ScreenSwap(new ScreenWithoutTransitions(), new ScreenWithoutTransitions(), TransitionDirection.ENTER);
        final ScreenSwap exitScreenSwap =
                new ScreenSwap(new ScreenWithoutTransitions(), new ScreenWithoutTransitions(), TransitionDirection.EXIT);
        final ViewController viewControllerWithoutTransitions = new ViewControllerWithoutTransitions();

        final Class<? extends ScreenTransition> enterTransition =
                UiContainer.getTransition(viewControllerWithoutTransitions, enterScreenSwap).getClass();
        final Class<? extends ScreenTransition> exitTransition =
                UiContainer.getTransition(viewControllerWithoutTransitions, exitScreenSwap).getClass();

        Assert.assertEquals(enterTransition, InstantTransition.class);
        Assert.assertEquals(exitTransition, InstantTransition.class);
    }

    @Test
    public void enterTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final ScreenSwap screenSwap =
                new ScreenSwap(new ScreenWithTransitionWithoutDefaultConstructor(), new ScreenWithTransitionWithoutDefaultConstructor(),
                        TransitionDirection.ENTER);
        UiContainer.getTransition(null, screenSwap);
    }

    @Test
    public void exitTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        final ScreenSwap screenSwap =
                new ScreenSwap(new ScreenWithTransitionWithoutDefaultConstructor(), new ScreenWithTransitionWithoutDefaultConstructor(),
                        TransitionDirection.ENTER);
        UiContainer.getTransition(null, screenSwap);
    }

    @EnterTransition(ForwardSlideTransition.class)
    @ExitTransition(BackwardSlideTransition.class)
    @Layout(0)
    static class ScreenWithTransitions extends Screen {
    }

    @EnterTransition(HorizontalSlideTransition.class)
    @ExitTransition(HorizontalSlideTransition.class)
    @Layout(0)
    static class ScreenWithTransitionWithoutDefaultConstructor extends Screen {
    }

    static class ScreenWithoutTransitions extends Screen {
    }

    static class ViewControllerWithTransitions extends ViewController {

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

    static class ViewControllerWithoutTransitions extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
