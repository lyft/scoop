package com.lyft.scoop;

public class ScreenSwap {

    public final Scoop scoop;
    public final Screen previous;
    public final Screen next;
    public final TransitionDirection direction;

    public ScreenSwap(Scoop scoop, Screen previous, Screen next, TransitionDirection direction) {
        this.scoop = scoop;
        this.previous = previous;
        this.next = next;

        this.direction = direction;
    }
}
