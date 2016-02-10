package com.example.scoop.basics.scoop;

import com.lyft.scoop.Scoop;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;

public class DaggerScreenScooper extends ScreenScooper {

    @Override
    protected Scoop addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        DaggerInjector parentDagger = DaggerInjector.fromScoop(parentScoop);

        ControllerModule controllerModule = screen.getView().getAnnotation(ControllerModule.class);

        if (controllerModule == null) {
            return parentScoop;
        }

        DaggerInjector screenInjector;

        try {
            Object module = controllerModule.value().newInstance();
            screenInjector = parentDagger.extend(module);
        } catch (Throwable e) {
            throw new RuntimeException("Module could not be instantiated for the view: " + screen.getView().getSimpleName(), e);
        }

        return scoopBuilder
                .service(DaggerInjector.SERVICE_NAME, screenInjector).build();
    }
}
