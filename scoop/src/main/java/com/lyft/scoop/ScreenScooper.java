package com.lyft.scoop;

import java.util.List;

public class ScreenScooper {

    private ScreenScoopFactory screenScoopFactory;

    public ScreenScooper(ScreenScoopFactory screenScoopFactory) {
        this.screenScoopFactory = screenScoopFactory;
    }

    public Scoop create(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {
        Scoop finalScoop = null;

        if (currentScreenScoop == null) {
            if (!toPath.isEmpty()) {
                finalScoop = rootScoop;

                for (Screen screen : toPath) {
                    finalScoop = screenScoopFactory.createScreenScoop(screen, finalScoop);
                }

                return finalScoop;
            }

            return finalScoop;
        }

        int index = incrementIndex(fromPath, toPath);

        return identifyRouteAndBuildScoop(index, rootScoop, currentScreenScoop, fromPath, toPath);
    }

    private Scoop identifyRouteAndBuildScoop(int index, Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath,
            List<Screen> toPath) {
        if (replaceAllWith(index)) {
            destroyStack(currentScreenScoop, fromPath);
            return createNewStack(rootScoop, toPath);
        } else if (resetTo(index, fromPath, toPath)) {
            Scoop resetScoop = goBack(index, currentScreenScoop, fromPath);
            return goForward(index, resetScoop, toPath);
        } else if (goTo(index, fromPath)) {
            return goForward(index, currentScreenScoop, toPath);
        } else {
            return goBack(index, currentScreenScoop, fromPath);
        }
    }

    private boolean replaceAllWith(int index) {
        return index == 0;
    }

    private boolean resetTo(int index, List<Screen> fromPath, List<Screen> toPath) {
        return index < fromPath.size() && index < toPath.size();
    }

    private boolean goTo(int index, List<Screen> fromPath) {
        return index >= fromPath.size();
    }

    private void destroyStack(Scoop currentScreenScoop, List<Screen> fromPath) {
        int index = 0;
        Scoop previousScoop = currentScreenScoop;
        while (index < fromPath.size()) {
            previousScoop.destroy();
            previousScoop = previousScoop.getParent();
            index++;
        }
    }

    private Scoop createNewStack(Scoop rootScoop, List<Screen> toPath) {
        if (toPath.isEmpty()) {
            return null;
        }
        Scoop nextScoop = rootScoop;
        int index = 0;
        while (index < toPath.size()) {
            nextScoop = screenScoopFactory.createScreenScoop(toPath.get(index), nextScoop);
            index++;
        }
        return nextScoop;
    }

    private int incrementIndex(List<Screen> fromPath, List<Screen> toPath) {
        int index = 0;
        while (index < fromPath.size() && index < toPath.size() && fromPath.get(index).equals(toPath.get(index))) {
            index++;
        }
        return index;
    }

    private Scoop goForward(int index, Scoop scoop, List<Screen> toPath) {
        Scoop nextScoop = scoop;
        int count = index;
        while (count < toPath.size()) {
            nextScoop = screenScoopFactory.createScreenScoop(toPath.get(count), nextScoop);
            count++;
        }
        return nextScoop;
    }

    private Scoop goBack(int index, Scoop scoop, List<Screen> fromPath) {
        Scoop previousScoop = scoop;
        int count = fromPath.size() - index;
        while (count > 0) {
            previousScoop.destroy();
            previousScoop = previousScoop.getParent();
            count--;
        }
        return previousScoop;
    }
}
