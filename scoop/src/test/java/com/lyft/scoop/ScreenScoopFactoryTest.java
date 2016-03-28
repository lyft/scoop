package com.lyft.scoop;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ScreenScoopFactoryTest {

    private ScreenScoopFactory screenScoopFactory;
    private Scoop rootScoop;
    private ScreenScooper screenScooper;

    @Before
    public void setUp() {
        screenScooper = new ScreenScooper();
        screenScoopFactory = new ScreenScoopFactory(screenScooper);
        rootScoop = new Scoop.Builder("root").build();
    }

    // [ ] - > [ A ]
    @Test
    public void createScoopForPathWhenPreviousPathWasEmpty() {

        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, null, Collections.<Screen>emptyList(), toPath);

        assertEquals(rootScoop, scoop.getParent());
    }

    // [ A ] - > [ A, B]
    @Test
    public void createScoopForNotEmptyPathToPathWithExtraScreen() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenB());

        Scoop aScoop = screenScooper.createScreenScoop(new ScreenA(), rootScoop);

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, aScoop, fromPath, toPath);

        assertEquals(aScoop, scoop.getParent());
    }

    static class ScreenA extends Screen {

    }

    static class ScreenB extends Screen {

    }
}
