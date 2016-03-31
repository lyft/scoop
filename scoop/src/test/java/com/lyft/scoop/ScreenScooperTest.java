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

    // [ ] - > [ A, B ]
    @Test
    public void createScoopFromEmptyPathToABPath() {

        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());

        Scoop bScoop = screenScooper.create(rootScoop, null, Collections.<Screen>emptyList(), toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(bScoop.getParent().isDestroyed());
        assertFalse(bScoop.isDestroyed());
        assertEquals(rootScoop, bScoop.getParent().getParent());
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

    // [ A ] - > [ A, B]
    @Test
    public void createScoopFromAPathToABPathWithNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());

        Scoop bScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(bScoop.isDestroyed());
        assertEquals(rootScoop, bScoop.getParent().getParent());
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

    // [ A, B ] - > [ A ]
    @Test
    public void createScoopFromABPathToAPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop scoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertEquals(rootScoop, scoop.getParent());
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

    // [ A, B ] - > [  ]
    @Test
    public void createScoopFromABPathToEmptyPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList();

        Scoop scoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());

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

    // [ A, B, C ] - > [ A ]
    @Test
    public void createScoopFromABCPathToAPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA());

        Scoop scoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(scoop.isDestroyed());
        assertFalse(rootScoop.isDestroyed());
        assertEquals(rootScoop, scoop.getParent());
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

    // [ A ] - > [ A, B, C ]
    @Test
    public void createScoopFromAPathToABCPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());

        Scoop cScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        //A
        assertFalse(cScoop.getParent().getParent().isDestroyed());
        //B
        assertFalse(cScoop.getParent().isDestroyed());
        assertFalse(cScoop.isDestroyed());
        assertFalse(rootScoop.isDestroyed());
        assertEquals(rootScoop, cScoop.getParent().getParent().getParent());
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

    // [ A ] - > [ B ]
    @Test
    public void createScoopFromAPathToBPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenB());

        Scoop bScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

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

    // [ A, B ] - > [ A, C ]
    @Test
    public void createScoopFromABPathToACPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenC());

        Scoop cScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        //A
        assertFalse(cScoop.getParent().isDestroyed());
        assertFalse(cScoop.isDestroyed());
        assertEquals(rootScoop, cScoop.getParent().getParent());
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

    // [ A, B ] - > [ C, D ]
    @Test
    public void createScoopFromABPathToCDPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenC(), new ScreenD());

        Scoop dScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(dScoop.getParent().isDestroyed());
        assertFalse(dScoop.isDestroyed());
        assertEquals(rootScoop, dScoop.getParent().getParent());
    }

    // [ A, B ] - > [ A, B, C ]
    @Test
    public void createScoopFromABPathToABCPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);

        Scoop cScoop = screenScooper.create(rootScoop, bScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertFalse(bScoop.isDestroyed());
        assertFalse(cScoop.isDestroyed());
        assertEquals(rootScoop, cScoop.getParent().getParent().getParent());
    }

    // [ A, B ] - > [ A, B, C ]
    @Test
    public void createScoopFromABPathToABCPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());

        Scoop cScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(cScoop.getParent().getParent().isDestroyed());
        assertFalse(cScoop.getParent().isDestroyed());
        assertFalse(cScoop.isDestroyed());
        assertEquals(rootScoop, cScoop.getParent().getParent().getParent());
    }

    // [ A, B, C ] - > [ A, B, D ]
    @Test
    public void createScoopFromABCPathToABDPath() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenD());

        Scoop aScoop = screenScoopFactory.createScreenScoop(new ScreenA(), rootScoop);
        Scoop bScoop = screenScoopFactory.createScreenScoop(new ScreenB(), aScoop);
        Scoop cScoop = screenScoopFactory.createScreenScoop(new ScreenC(), bScoop);

        Scoop dScoop = screenScooper.create(rootScoop, cScoop, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(aScoop.isDestroyed());
        assertFalse(bScoop.isDestroyed());
        assertFalse(dScoop.isDestroyed());
        assertTrue(cScoop.isDestroyed());
        assertEquals(rootScoop, dScoop.getParent().getParent().getParent());
    }

    // [ A, B, C ] - > [ A, B, D ]
    @Test
    public void createScoopFromABCPathToABDPathNullCurrentScoop() {

        List<Screen> fromPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenC());
        List<Screen> toPath = Arrays.<Screen>asList(new ScreenA(), new ScreenB(), new ScreenD());

        Scoop dScoop = screenScooper.create(rootScoop, null, fromPath, toPath);

        assertFalse(rootScoop.isDestroyed());
        assertFalse(dScoop.getParent().getParent().isDestroyed());
        assertFalse(dScoop.getParent().isDestroyed());
        assertFalse(dScoop.isDestroyed());
        assertEquals(rootScoop, dScoop.getParent().getParent().getParent());
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
