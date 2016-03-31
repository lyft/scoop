package com.lyft.scoop;

import java.util.List;

public class ScreenScooper {

    private ScreenScoopFactory screenScoopFactory;

    public ScreenScooper(ScreenScoopFactory screenScoopFactory) {
        this.screenScoopFactory = screenScoopFactory;
    }

    public Scoop create(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {
        Scoop finalScoop = null;

        Screen toPathFirstScreen = getFirstScreen(toPath);

        Scoop startScoop = findStartScoop(rootScoop, currentScreenScoop, toPathFirstScreen);

        if (startScoop == null) {
            if (!toPath.isEmpty()) {
                finalScoop = rootScoop;

                for (Screen screen : toPath) {
                    finalScoop = screenScoopFactory.createScreenScoop(screen, finalScoop);
                }
            }
        } else {
            finalScoop = startScoop;
            for (int i = 1; i < toPath.size(); i++) {
                Screen screen = toPath.get(i);
                finalScoop = screenScoopFactory.createScreenScoop(screen, finalScoop);
            }
        }

        return finalScoop;
    }

    private Scoop findStartScoop(Scoop rootScoop, Scoop startScoop, Screen toPathFirstScreen) {
        if (rootScoop.equals(startScoop) || startScoop == null) {
            return null;
        }

        Screen scoopScreen = Screen.fromScoop(startScoop);

        if (!Screen.equals(scoopScreen, toPathFirstScreen)) {
            startScoop.destroy();
            startScoop = startScoop.getParent();

            startScoop = findStartScoop(rootScoop, startScoop, toPathFirstScreen);
        }

        return startScoop;
    }

    private Screen getFirstScreen(List<Screen> path) {
        if (path.isEmpty()) {
            return null;
        }

        return path.get(0);
    }
}
