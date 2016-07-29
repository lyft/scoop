package com.lyft.scoop;

public class ScreenSwap {

    public final Screen previous;
    public final Screen next;
    public final TransitionDirection direction;

    public ScreenSwap(Screen previous, Screen next, TransitionDirection direction) {
        this.previous = previous;
        this.next = next;
        this.direction = direction;
    }
}
