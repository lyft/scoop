package com.lyft.scoop;

public class ScreenScooper {

    public final Scoop createScreenScoop(Screen screen, Scoop parentScoop) {

        Scoop.Builder scoopBuilder = new Scoop.Builder(screen.getController().getSimpleName(), parentScoop)
                .service(Screen.SERVICE_NAME, screen);

        return addServices(scoopBuilder, screen, parentScoop).build();
    }

    protected Scoop.Builder addServices(Scoop.Builder scoopBuilder, Screen screen, Scoop parentScoop) {
        return scoopBuilder;
    }
}
