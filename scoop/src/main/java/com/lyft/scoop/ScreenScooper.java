package com.lyft.scoop;

public class ScreenScooper {

    public final Scoop createScreenScoop(Screen screen, Scoop parentScoop) {

        Scoop.Builder scoopBuilder = new Scoop.Builder(screen.getView().getSimpleName(), parentScoop)
                .service(Screen.SERVICE_NAME, screen);

        return addServices(scoopBuilder, screen, parentScoop);
    }

    protected Scoop addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        return scoopBuilder.build();
    }
}
