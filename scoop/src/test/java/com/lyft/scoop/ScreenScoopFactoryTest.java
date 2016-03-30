package com.lyft.scoop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ScreenScoopFactoryTest {

    @Test
    public void screenAddedToScoop() {
        ScreenScoopFactory screenScoopFactory = new ScreenScoopFactory();

        Scoop parentScoop = new Scoop.Builder("root").build();

        Screen screen = new TestScreen();

        Scoop childScoop = screenScoopFactory.createScreenScoop(screen, parentScoop);

        Assert.assertEquals(screen, Screen.fromScoop(childScoop));
    }

    @Layout(0)
    static class TestScreen extends Screen {
    }
}
