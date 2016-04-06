package com.lyft.scoop;

import java.util.List;

public class RouteChange {

    public final List<Screen> fromPath;
    public final List<Screen> toPath;
    public final TransitionDirection direction;

    public RouteChange(List<Screen> fromPath, List<Screen> toPath, TransitionDirection direction) {
        this.fromPath = fromPath;
        this.toPath = toPath;
        this.direction = direction;
    }

    public ScreenSwap toScreenSwap(Scoop scoop) {
        return new ScreenSwap(scoop,
                this.previousScreen(),
                this.nextScreen(),
                this.direction);
    }

    public Screen previousScreen() {
        return getScreenFromPath(fromPath);
    }

    public Screen nextScreen() {
        return getScreenFromPath(toPath);
    }

    private Screen getScreenFromPath(List<Screen> path) {
        Screen screen = null;

        if (!path.isEmpty()) {
            screen = path.get(path.size() - 1);
        }

        return screen;
    }
}
