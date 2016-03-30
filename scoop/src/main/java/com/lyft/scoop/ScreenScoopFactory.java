package com.lyft.scoop;

import java.util.List;

public class ScreenScoopFactory {

    private ScreenScooper screenScooper;

    public ScreenScoopFactory(ScreenScooper screenScooper) {
        this.screenScooper = screenScooper;
    }

    public Scoop createScoop(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {
        if (toPath.isEmpty()) {
            destroyStack(currentScreenScoop, fromPath);
            return null;
        } else if (fromPath.isEmpty()) {
            return screenScooper.createScreenScoop(toPath.get(toPath.size() - 1), rootScoop);
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
        Scoop nextScoop = rootScoop;
        int index = 0;
        while (index < toPath.size()) {
            nextScoop = screenScooper.createScreenScoop(toPath.get(index), nextScoop);
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
            nextScoop = screenScooper.createScreenScoop(toPath.get(count), nextScoop);
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
