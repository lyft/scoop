package com.lyft.scoop;

import com.lyft.scoop.transitions.TransitionDirection;

public class RouteChange {

    public final Scoop scoop;
    public final Screen previous;
    public final Screen next;
    public final TransitionDirection direction;

    public RouteChange(Scoop scoop, Screen previous, Screen next, TransitionDirection direction) {
        this.scoop = scoop;
        this.previous = previous;
        this.next = next;

        this.direction = direction;
    }
}
