package com.lyft.scoop;

import org.junit.Assert;
import org.junit.Test;

public class ScoopTest {

    @Test
    public void createRootScoop() {
        FooService service = new FooService();
        Scoop scoop = new Scoop.Builder("root")
                .service("foo_service", service)
                .build();

        Assert.assertEquals("root", scoop.getName());
        Assert.assertNull(scoop.getParent());
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
                .service("foo_service_2", service1)
                .build();

        rootScoop.destroy();

        Assert.assertTrue(rootScoop.isDestroyed());
        Assert.assertNull(rootScoop.findChild("child"));
        Assert.assertNull(rootScoop.findService("foo_service_1"));

        Assert.assertTrue(childScoop.isDestroyed());
        Assert.assertNull(childScoop.getParent());
        Assert.assertNull(rootScoop.findService("foo_service_2"));
    }

    static class FooService {

    }
}
