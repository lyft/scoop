package com.lyft.scoop;

import java.util.List;

public class ScreenScooper {

    private ScreenScoopFactory screenScoopFactory;

    public ScreenScooper(ScreenScoopFactory screenScoopFactory) {
        this.screenScoopFactory = screenScoopFactory;
    }

    public Scoop create(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {
        Scoop finalScoop = null;
        Scoop startScoop = currentScreenScoop;

        Screen toPathFirstScreen = getFirstScreen(toPath);

        while (!rootScoop.equals(startScoop) && startScoop != null) {
            Screen scoopScreen = Screen.fromScoop(startScoop);

            if (Router.sameScreen(scoopScreen, toPathFirstScreen)) {
                break;
            } else {
                startScoop.destroy();
                startScoop = startScoop.getParent();
            }
        }

        if (rootScoop.equals(startScoop) || startScoop == null) {
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

    private Screen getFirstScreen(List<Screen> path) {
        if (path.isEmpty()) {
            return null;
        }

        return path.get(0);
    }
}
