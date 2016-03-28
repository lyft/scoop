package com.lyft.scoop;

import java.util.List;

public class ScreenScoopFactory {

    private ScreenScooper screenScooper;

    public ScreenScoopFactory(ScreenScooper screenScooper) {
        this.screenScooper = screenScooper;
    }

    public Scoop createScoop(Scoop rootScoop, Scoop currentScreenScoop, List<Screen> fromPath, List<Screen> toPath) {
        if (toPath.isEmpty()) {
            return rootScoop;
        }
        if (fromPath.isEmpty()) {
            return screenScooper.createScreenScoop(toPath.get(toPath.size() - 1), rootScoop);
        }
        int index = incrementIndex(fromPath, toPath);

        if (index == 0) {
            return createNewStack(rootScoop, toPath);
        }
        if (index >= fromPath.size()) {
            return goForward(index, currentScreenScoop, toPath);
        } else {
            return goBack(index, currentScreenScoop, fromPath);
        }
    }

    private Scoop createNewStack(Scoop rootScoop, List<Screen> toPath) {
        Scoop nextScoop = rootScoop;
        int index = 0;
        while (index < toPath.size()) {
            nextScoop = screenScooper.createScreenScoop(toPath.get(index), nextScoop);
            index ++;
        }
        return nextScoop;
    }

    private int incrementIndex(List<Screen> fromPath, List<Screen> toPath) {
        int index = 0;
        while(index < fromPath.size() && index < toPath.size() && fromPath.get(index).equals(toPath.get(index))) {
            index++;
        }
        return index;
    }

    private Scoop goForward(int index, Scoop currentScreenScoop, List<Screen> toPath) {
        Scoop nextScoop = currentScreenScoop;
        int count = index;
        while (count < toPath.size()) {
            nextScoop = screenScooper.createScreenScoop(toPath.get(count), nextScoop);
            count ++;
        }
        return nextScoop;
    }

    private Scoop goBack(int index, Scoop currentScreenScoop, List<Screen> fromPath) {
        Scoop previousScoop = currentScreenScoop;
        int count = fromPath.size() - index;
        while (count > 0) {
            previousScoop = previousScoop.getParent();
            count --;
        }
        return previousScoop;
    }
}
