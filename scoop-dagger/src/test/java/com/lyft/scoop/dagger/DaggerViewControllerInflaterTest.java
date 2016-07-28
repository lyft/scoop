package com.lyft.scoop.dagger;

import com.lyft.scoop.Scoop;
import com.lyft.scoop.ViewController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class DaggerViewControllerInflaterTest {

    @Mock
    DaggerInjector mockDaggerInjector;

    private DaggerViewControllerInflater daggerViewControllerInflater;
    private Scoop.Builder scoopBuilder;
    private Scoop scoop;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        daggerViewControllerInflater = new DaggerViewControllerInflater();
        scoop = new Scoop.Builder("root").service(DaggerInjector.SERVICE_NAME, mockDaggerInjector).build();
        scoopBuilder = new Scoop.Builder("child");
    }

    @Test
    public void testCreateViewController() {
        daggerViewControllerInflater.createViewController(scoop, TestViewController.class);
        verify(mockDaggerInjector).get(eq(TestViewController.class));
    }

    static class TestViewController extends ViewController {

        @Override
        protected int layoutId() {
            return 0;
        }
    }
}
