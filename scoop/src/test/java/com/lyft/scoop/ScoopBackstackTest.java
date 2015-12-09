package com.lyft.scoop;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScoopBackstackTest {

    private ScoopBackstack backStack;

    @Before
    public void setUp() {
        backStack = new ScoopBackstack();
    }

    @Test
    public void pushSingleScoop() {
        Scoop scoop = new Scoop.Builder("foo").build();
        backStack.push(scoop);
        Assert.assertFalse(backStack.isEmpty());
        Assert.assertEquals(scoop, backStack.peek());
    }

    @Test
    public void popDestroyScoop() {
        Scoop scoop = new Scoop.Builder("foo").build();
        backStack.push(scoop);
        backStack.pop();
        Assert.assertTrue(scoop.isDestroyed());
    }
}
