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
        Assert.assertNotNull(UiContainer.getEnterTransition(Screen.create(ViewControllerWithTransitions.class)));
        Assert.assertNotNull(UiContainer.getExitTransition(Screen.create(ViewControllerWithTransitions.class)));
    }

    @Test
    public void enterTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        UiContainer.getEnterTransition(Screen.create(ViewControllerWithTransitionWithoutDefaultConstructor.class));
    }

    @Test
    public void exitTransitionWithoutDefaultConstructor() {
        exception.expect(RuntimeException.class);
        UiContainer.getExitTransition(Screen.create(ViewControllerWithTransitionWithoutDefaultConstructor.class));
    }

    @Test
    public void useInstantTransitionIfControllerHasNoTransitions() {
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getEnterTransition(Screen.create(ViewControllerWithoutTransitions.class)).getClass());
        Assert.assertEquals(InstantTransition.class,
                UiContainer.getExitTransition(Screen.create(ViewControllerWithoutTransitions.class)).getClass());
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
