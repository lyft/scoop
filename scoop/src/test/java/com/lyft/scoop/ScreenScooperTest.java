package com.lyft.scoop;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScreenScooperTest {

    private ScreenScooper screenScooper;
    private Scoop rootScoop;
    private ScreenScoopFactory screenScoopFactory;

    @Before
    public void setUp() {
        screenScoopFactory = new ScreenScoopFactory();
        screenScooper = new ScreenScooper(screenScoopFactory);
        rootScoop = new Scoop.Builder("root").build();
    }

    // [ ] - > [  ]
    @Test
    public void createScoopFromEmptyPathToEmptyPath() {

        Scoop scoop = screenScooper.create(rootScoop, null, Collections.<Screen>emptyList(), Collections.<Screen>emptyList());

        assertFalse(rootScoop.isDestroyed());
        assertNull(scoop);
    }

    // [ ] - > [ A ]
    @Test
    public void createScoopFromEmptyPathToAPath() {

        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop aScoop = screenScooper.create(rootScoop, null, Collections.<Screen>emptyList(), toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertEquals(rootScoop, aScoop.getParent());
    }

    // [ A ] - > [ A, B]
    @Test
    public void createScoopFromAPathToABPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);

        Scoop bScoop = screenScooper.create(rootScoop, aScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertFalse(bScoop.isDestroyed());
        assertEquals(aScoop, bScoop.getParent());
    }

    // [ A, B ] - > [ A ]
    @Test
    public void createScoopFromABPathToAPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);

        Scoop scoop = screenScooper.create(rootScoop, bScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertTrue(bScoop.isDestroyed());
        assertEquals(aScoop, scoop);
    }

    // [ A, B ] - > [  ]
    @Test
    public void createScoopFromABPathToEmptyPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList();

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);

        Scoop scoop = screenScooper.create(rootScoop, bScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertTrue(aScoop.isDestroyed());
        assertTrue(bScoop.isDestroyed());

        assertNull(scoop);
    }

    // [ A, B, C ] - > [ A ]
    @Test
    public void createScoopFromABCPathToAPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);
        Scoop cScoop = screenScoopFactory.createScreenScoop(new ScreenC(), bScoop);

        Scoop scoop = screenScooper.create(rootScoop, cScoop, fromPath, toPath);

        assertTrue(bScoop.isDestroyed());
        assertTrue(cScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertFalse(rootScoop.isDestroyed());
        assertEquals(aScoop, scoop);
    }

    // [ A ] - > [ A, B, C ]
    @Test
    public void createScoopFromAPathToABCPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);

        Scoop cScoop = screenScooper.create(rootScoop, aScoop, fromPath, toPath);

        assertFalse(cScoop.getParent().isDestroyed());
        assertFalse(cScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertFalse(rootScoop.isDestroyed());
        assertEquals(aScoop, cScoop.getParent().getParent());
    }

    // [ A ] - > [ B ]
    @Test
    public void createScoopFromAPathToBPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenB());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);

        Scoop bScoop = screenScooper.create(rootScoop, aScoop, fromPath, toPath);

        assertTrue(aScoop.isDestroyed());
        assertFalse(bScoop.isDestroyed());
        assertFalse(rootScoop.isDestroyed());
        assertEquals(rootScoop, bScoop.getParent());
    }

    // [ A, B ] - > [ A, C ]
    @Test
    public void createScoopFromABPathToACPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenC());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);

        Scoop cScoop = screenScooper.create(rootScoop, bScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertTrue(bScoop.isDestroyed());
        assertFalse(cScoop.isDestroyed());
        assertEquals(aScoop, cScoop.getParent());
    }

    // [ A, B ] - > [ C, D ]
    @Test
    public void createScoopFromABPathToCDPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenC(), new ScreenD());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);

        Scoop dScoop = screenScooper.create(rootScoop, bScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertTrue(aScoop.isDestroyed());
        assertTrue(bScoop.isDestroyed());
        assertFalse(dScoop.getParent().isDestroyed());
        assertFalse(dScoop.isDestroyed());
        assertEquals(rootScoop, dScoop.getParent().getParent());
    }

    static class ScreenA extends Screen {

    }

    static class ScreenB extends Screen {

    }

    static class ScreenC extends Screen {

    }

    static class ScreenD extends Screen {

    }
}
