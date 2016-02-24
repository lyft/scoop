package com.lyft.scoop.dagger;

import android.view.View;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.Screen;
import dagger.ObjectGraph;

public class DaggerInjector {

    public static final String SERVICE_NAME = "dagger";
    private ObjectGraph objectGraph;

    public DaggerInjector(ObjectGraph objectGraph) {
        this.objectGraph = objectGraph;
    }

    public <T> void inject(T obj) {
        objectGraph.inject(obj);
    }

    public <T> T get(Class<T> clazz) {
        return objectGraph.get(clazz);
    }

    public DaggerInjector extend(Object... modules) {
        ObjectGraph objectGraph = this.objectGraph.plus(modules);
        return new DaggerInjector(objectGraph);
    }

    public static DaggerInjector fromScoop(Scoop scoop) {
        return scoop.findService(SERVICE_NAME);
    }

    public static DaggerInjector fromView(View view) {
        return Scoop.fromView(view).findService(SERVICE_NAME);
    }

    public static Scoop extend(final Scoop parentScoop, final Object... modules) {
        final Screen parentScreen = Screen.fromScoop(parentScoop);
        final Scoop.Builder scoopBuilder = new Scoop.Builder(parentScreen.getClass().getSimpleName(), parentScoop)
                .service(Screen.SERVICE_NAME, parentScreen);
        final DaggerInjector daggerInjector = DaggerInjector.fromScoop(parentScoop).extend(modules);

        return scoopBuilder
                .service(DaggerInjector.SERVICE_NAME, daggerInjector).build();
    }
}
