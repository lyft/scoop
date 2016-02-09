package com.example.scoop.basics.scoop;

import com.lyft.scoop.Scoop;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;

public class DaggerScreenScooper extends ScreenScooper {

    @Override
    protected Scoop addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        DaggerInjector parentDagger = DaggerInjector.fromScoop(parentScoop);

        Class<?> controllerModule = screen.getModule();

        if (controllerModule == null) {
            return parentScoop;
        }

        DaggerInjector screenInjector;

        try {
            Object module = controllerModule.newInstance();
            screenInjector = parentDagger.extend(module);
        } catch (Throwable e) {
            throw new RuntimeException("Module not specified for " + screen.getName(), e);
        }

        return scoopBuilder
                .service(DaggerInjector.SERVICE_NAME, screenInjector).build();
    }
}
