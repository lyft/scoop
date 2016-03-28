package com.lyft.scoop;

import java.util.List;

public class ScreenScoopFactory {

    private ScreenScooper screenScooper;

    public ScreenScoopFactory(ScreenScooper screenScooper) {
        this.screenScooper = screenScooper;
    }

    public Scoop createScoop(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {

        Scoop scoop = screenScooper.createScreenScoop(toPath.get(0), rootScoop);

        return scoop;
    }
}
