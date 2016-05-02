package com.lyft.scoop;

import android.content.Context;
import android.view.View;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ViewControllerTest {
    private static final String TEST_RESULT = "TEST_RESULT";

    private MockViewController viewController;
    private View mockView;

    @Before
    public void setUp() {
        viewController = new MockViewController();
        mockView = new TestView(RuntimeEnvironment.application);
    }
    @Test
    public void onAttachFirst() {
        viewController.attach(mockView);
        assertTrue(viewController.attached());
        viewController.detach(mockView);
        assertFalse(viewController.attached());
    }

    @Test
    public void onDetachFirst() {
        viewController.detach(mockView);
        assertFalse(viewController.attached());
        viewController.attach(mockView);
        assertFalse(viewController.attached());
    }

    private class MockViewController extends ViewController {
        private String variable = null;

        @Override
        public void onAttach() {
            variable = TEST_RESULT;
        }

        @Override
        public void onDetach() {
            variable.toString();
        }

        @Override
        protected int layoutId() {
            return 0;
        }
    }

    static class TestView extends View {

        public TestView(Context context) {
            super(context);
        }
    }
}
