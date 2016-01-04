package com.example.scoop.basics.scoop;

import com.lyft.scoop.Scoop;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ScreenScooper;

public class DaggerScreenScooper extends ScreenScooper {

    public static final String SCREEN_CONTROLLER = "screen_controller";
    
    @Override
    protected Scoop.Builder addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        InjectThat injectThat = screen.getController().getAnnotation(InjectThat.class);

        try {
            Injector injector = injectThat.value().newInstance();
            injector.configureScope(scoopBuilder, parentScoop);

            scoopBuilder.service(SCREEN_CONTROLLER, injector.getController());
        } catch (Throwable e) {
            throw new RuntimeException("Injector not specified for " + screen.getController().getSimpleName(), e);
        }

        return scoopBuilder;
    }
}
