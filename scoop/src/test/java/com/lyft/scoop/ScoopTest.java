package com.lyft.scoop;

import android.content.Context;
import android.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ScoopTest {
    private static final Scoop TEST_SCOOP = new Scoop.Builder("root").build();

    @Test
    public void createRootScoop() {
        FooService service = new FooService();
        Scoop scoop = new Scoop.Builder("root")
                .service("foo_service", service)
                .build();

        Assert.assertEquals("root", scoop.getName());
        assertNull(scoop.getParent());
        Assert.assertEquals(service, scoop.findService("foo_service"));
    }

    @Test
    public void createChildScoop() {
        Scoop rootScoop = new Scoop.Builder("root").build();

        Scoop childScoop = new Scoop.Builder("child", rootScoop)
                .build();

        Assert.assertEquals("child", childScoop.getName());
        Assert.assertEquals(rootScoop, childScoop.getParent());
        Assert.assertEquals(childScoop, rootScoop.findChild("child"));
    }

    @Test
    public void findService() {
        FooService service = new FooService();

        Scoop rootScoop = new Scoop.Builder("root")
                .service("foo_service", service)
                .build();

        Assert.assertEquals(service, rootScoop.findService("foo_service"));
    }

    @Test
    public void findServiceInParent() {
        FooService service = new FooService();

        Scoop rootScoop = new Scoop.Builder("root")
                .service("foo_service", service)
                .build();

        Scoop childScoop = new Scoop.Builder("child", rootScoop)
                .build();

        Assert.assertEquals(service, childScoop.findService("foo_service"));
    }

    @Test
    public void destroyRootScoop() {
        FooService service1 = new FooService();

        Scoop rootScoop = new Scoop.Builder("root")
                .service("foo_service_1", service1)
                .build();

        FooService service2 = new FooService();

        Scoop childScoop = new Scoop.Builder("child", rootScoop)
                .service("foo_service_2", service2)
                .build();

        FooService service3 = new FooService();

        Scoop grandChildScoop = new Scoop.Builder("grand_child", childScoop)
                .service("foo_service_3", service3)
                .build();

        childScoop.destroy();

        Assert.assertFalse(rootScoop.isDestroyed());
        assertNull(rootScoop.findChild("child"));
        Assert.assertNotNull(rootScoop.findService("foo_service_1"));

        Assert.assertTrue(childScoop.isDestroyed());
        Assert.assertNotNull(childScoop.getParent());
        assertNull(childScoop.findChild("grand_child"));
        Assert.assertNotNull(childScoop.findService("foo_service_1"));
        Assert.assertNotNull(childScoop.findService("foo_service_2"));

        Assert.assertTrue(grandChildScoop.isDestroyed());
        Assert.assertNotNull(grandChildScoop.getParent());
        Assert.assertNotNull(grandChildScoop.findService("foo_service_1"));
        Assert.assertNotNull(grandChildScoop.findService("foo_service_2"));
        Assert.assertNotNull(grandChildScoop.findService("foo_service_3"));
    }

    @Test
    public void fromNullView() {
        assertNull(Scoop.fromView(null));
    }

    @Test
    public void fromViewNoScoop() {
        try {
            Scoop.fromView(new TestView(RuntimeEnvironment.application));
            fail("Should have thrown a RuntimeException if there is no scoop in the view's context.");
        } catch (RuntimeException e) {
            //Expected result
        }

    }

    static class FooService {

    }

    static class TestView extends View {

        public TestView(Context context) {
            super(context);
        }
    }
}
