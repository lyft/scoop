package com.lyft.scoop;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScreenScoopFactoryTest {

    @Test
    public void createScoopForPathWhenPreviousPathWasEmpty() {
        ScreenScoopFactory screenScoopFactory = new ScreenScoopFactory(new ScreenScooper());

        Scoop rootScoop = new Scoop.Builder("root").build();

        List<Screen> toPath = Arrays.<Screen>asList(new Screen1());

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, null, Collections.<Screen>emptyList(), toPath);

        assertEquals(rootScoop, scoop.getParent());
    }

    static class Screen1 extends Screen {

    }
}
