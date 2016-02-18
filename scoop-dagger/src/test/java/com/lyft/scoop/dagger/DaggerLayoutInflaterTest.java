package com.lyft.scoop.dagger;

import dagger.ObjectGraph;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DaggerLayoutInflaterTest {

    private static final String TEST_STRING = "testString";

    @Mock
    DaggerInjector mockDaggerInjector;

    @Mock
    ObjectGraph mockObjectGraph;

    private DaggerInjector daggerInjector;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        daggerInjector = new DaggerInjector(mockObjectGraph);
    }

    @Test
    public void testInject() {
        daggerInjector.inject(TEST_STRING);
        verify(mockObjectGraph, times(1)).inject(eq(TEST_STRING));
    }

    @Test
    public void testGet() {
        when(mockObjectGraph.get(eq(String.class))).thenReturn(TEST_STRING);
        assertEquals(TEST_STRING, daggerInjector.get(String.class));
    }

    @Test
    public void testExtend() {
        when(mockObjectGraph.plus(eq(TEST_STRING))).thenReturn(mockObjectGraph);
        assertNotNull(daggerInjector.extend(TEST_STRING));
        verify(mockObjectGraph, times(1)).plus(eq(TEST_STRING));
    }
}
