package com.lyft.scoop;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    public void createScoopFromEmptyPathToAPath() {

        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, null, Collections.<Screen>emptyList(), toPath);

        assertEquals(rootScoop, scoop.getParent());
    }

    // [ A ] - > [ A, B]
    @Test
    public void createScoopFromAPathToABPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());

        Scoop aScoop = screenScooper.createScreenScoop(new ScreenA(), rootScoop);

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, aScoop, fromPath, toPath);

        assertEquals(aScoop, scoop.getParent());
    }

    // [ A, B ] - > [ A ]
    @Test
    public void createScoopFromABPathToAPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop aScoop = screenScooper.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScooper.createScreenScoop(new ScreenB(), aScoop);

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, bScoop, fromPath, toPath);

        assertTrue(bScoop.isDestroyed());
        assertEquals(aScoop, scoop);
    }

    // [ A, B, C ] - > [ A ]
    @Test
    public void createScoopFromABCPathToAPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop aScoop = screenScooper.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScooper.createScreenScoop(new ScreenB(), aScoop);
        Scoop cScoop = screenScooper.createScreenScoop(new ScreenC(), bScoop);

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, cScoop, fromPath, toPath);

        assertTrue(bScoop.isDestroyed());
        assertTrue(cScoop.isDestroyed());
        assertEquals(aScoop, scoop);
    }

    // [ A ] - > [ A, B, C ]
    @Test
    public void createScoopFromAPathToABCPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());

        Scoop aScoop = screenScooper.createScreenScoop(new ScreenA(), rootScoop);

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, aScoop, fromPath, toPath);

        assertEquals(aScoop, scoop.getParent().getParent());
    }

    // [ A ] - > [ B ]
    @Test
    public void createScoopFromAPathToBPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenB());

        Scoop aScoop = screenScooper.createScreenScoop(new ScreenA(), rootScoop);

        Scoop scoop = screenScoopFactory.createScoop(rootScoop, aScoop, fromPath, toPath);

        assertTrue(aScoop.isDestroyed());
        assertEquals(rootScoop, scoop.getParent());
    }

    static class ScreenA extends Screen {

    }

    static class ScreenB extends Screen {

    }

    static class ScreenC extends Screen {

    }
}
