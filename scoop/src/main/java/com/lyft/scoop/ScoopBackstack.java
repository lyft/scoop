package com.lyft.scoop;

import java.util.ArrayDeque;

class ScoopBackstack {

    ArrayDeque<Scoop> backStack = new ArrayDeque<>();

    public void pop() {
        Scoop poppedScoop = backStack.pop();
        poppedScoop.destroy();
    }

    public boolean isEmpty() {
        return backStack.isEmpty();
    }

    public Scoop peek() {
        return backStack.peek();
    }

    public void push(Scoop scoop) {
        backStack.push(scoop);
    }

    public void clear() {
        while(!backStack.isEmpty()) {
            pop();
        }
    }
}
