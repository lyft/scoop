package com.lyft.scoop;

import java.util.ArrayList;
import java.util.List;

public class ScreenScooper {

    private ScreenScoopFactory screenScoopFactory;

    public ScreenScooper(ScreenScoopFactory screenScoopFactory) {
        this.screenScoopFactory = screenScoopFactory;
    }

    public Scoop create(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {
        Scoop finalScoop = null;

        List<Scoop> scoops = getCurrentScoops(fromPath, currentScreenScoop);

        int index = 0;

        while (index < fromPath.size() && currentScreenScoop != null) {
            Screen fromScreen = safeElementGet(fromPath, index);
            Screen toScreen = safeElementGet(toPath, index);

            Scoop scoop = scoops.get(index);

            if (Screen.equals(fromScreen, toScreen)) {
                finalScoop = scoop;
            } else {
                scoop.destroy();
                break;
            }

            index++;
        }

        while (index < toPath.size()) {
            Screen toScreen = safeElementGet(toPath, index);

            if (finalScoop == null) {
                finalScoop = rootScoop;
            }

            finalScoop = screenScoopFactory.createScreenScoop(toScreen, finalScoop);

            index++;
        }

        return finalScoop;
    }

    private ArrayList<Scoop> getCurrentScoops(List<Screen> fromPath, Scoop currentScreenScoop) {

        ArrayList<Scoop> scoops = new ArrayList<>();

        Scoop startScoop = currentScreenScoop;

        if (startScoop != null) {
            for (int i = 0; i < fromPath.size(); i++) {
                scoops.add(0, startScoop);
                startScoop = startScoop.getParent();
            }
        }

        return scoops;
    }

    private <T> T safeElementGet(List<T> fromPath, int i) {
        if (i < fromPath.size()) {
            return fromPath.get(i);
        }
        return null;
    }
}
