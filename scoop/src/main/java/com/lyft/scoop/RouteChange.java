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

    //TODO: test me
    public ScreenSwap toScreenSwap(Scoop scoop) {
        return new ScreenSwap(scoop,
                this.sourceScreen(),
                this.destinationScreen(),
                this.direction);
    }

    private Screen sourceScreen() {
        Screen screen = null;

        if (!fromPath.isEmpty()) {
            screen = fromPath.get(fromPath.size() - 1);
        }

        return screen;
    }

    private Screen destinationScreen() {
        Screen screen = null;

        if (!toPath.isEmpty()) {
            screen = toPath.get(toPath.size() - 1);
        }

        return screen;
    }
}
