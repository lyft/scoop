package com.lyft.scoop;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class ScreenBackstack {

    ArrayDeque<Screen> backStack = new ArrayDeque<>();

    public void pop() {
        Screen poppedScreen = backStack.pop();
    }

    public boolean isEmpty() {
        return backStack.isEmpty();
    }

    public Screen peek() {
        return backStack.peek();
    }

    public void push(Screen screen) {
        backStack.push(screen);
    }

    public void clear() {
        while (!backStack.isEmpty()) {
            pop();
        }
    }

    public List<Screen> asList() {
        Screen[] array = backStack.toArray(new Screen[backStack.size()]);

        List<Screen> list = Arrays.asList(array);
        Collections.reverse(list);

        return list;
    }
}
