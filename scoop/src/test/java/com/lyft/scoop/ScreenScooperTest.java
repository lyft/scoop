package com.lyft.scoop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ScreenScooperTest {

    @Test
    public void screenAddedToScoop() {
        ScreenScooper screenScooper = new ScreenScooper();

        Scoop parentScoop = new Scoop.Builder("root").build();

        Screen screen = Screen.create(TestViewController.class);

        Scoop childScoop = screenScooper.createScreenScoop(screen, parentScoop);

        Assert.assertEquals(screen, Screen.fromScoop(childScoop));
    }

    static class TestViewController extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
