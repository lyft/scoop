package com.lyft.scoop;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Router {

    private ScreenBackstack backStack = new ScreenBackstack();
    private boolean allowEmptyStack;

    public Router() {
        this.allowEmptyStack = false;
    }

    public Router(boolean allowEmptyStack) {

        this.allowEmptyStack = allowEmptyStack;
    }

    public boolean goBack() {
        if (!backStack.isEmpty()) {
            List<Screen> fromPath = backStack.asList();

            //TODO if the backstack is Empty after this pop, and allowEmptyStack is false, nobody destroys the last scoop
            backStack.pop();

            if (!backStack.isEmpty()) {
                performRouteChange(fromPath, backStack.asList(), TransitionDirection.EXIT);
                return true;
            } else if (allowEmptyStack) {
                performRouteChange(fromPath, Collections.<Screen>emptyList(), TransitionDirection.EXIT);
                return true;
            }
        }

        return false;
    }

    public void goTo(Screen nextScreen) {
        if (tryHandleEmptyBackstack(nextScreen)) {
            return;
        }

        List<Screen> fromPath = backStack.asList();

        if (sameScreen(backStack.peek(), nextScreen)) {
            return;
        }

        backStack.push(nextScreen);

        performRouteChange(fromPath, backStack.asList(), TransitionDirection.ENTER);
    }

    public void replaceWith(Screen nextScreen) {
        if (tryHandleEmptyBackstack(nextScreen)) {
            return;
        }
        List<Screen> fromPath = backStack.asList();

        Screen previousScreen = backStack.peek();

        if (sameScreen(previousScreen, nextScreen)) {
            return;
        }

        if (!backStack.isEmpty()) {
            backStack.pop();
        }

        backStack.push(nextScreen);
        performRouteChange(fromPath, backStack.asList(), TransitionDirection.ENTER);
    }

    public void replaceAllWith(Screen... screens) {
        replaceAllWith(Arrays.asList(screens));
    }

    public void replaceAllWith(List<Screen> screens) {
        List<Screen> fromPath = backStack.asList();

        backStack.clear();

        for (final Screen screen : screens) {
            backStack.push(screen);
        }

        performRouteChange(fromPath, backStack.asList(), TransitionDirection.ENTER);
    }

    public void resetTo(Screen screen) {
        if (tryHandleEmptyBackstack(screen)) {
            return;
        }
        resetTo(screen, TransitionDirection.EXIT);
    }

    public void resetTo(Screen nextScreen, TransitionDirection direction) {
        List<Screen> fromPath = backStack.asList();

        // do nothing if screen already top of reset
        if (!backStack.isEmpty() && sameScreen(nextScreen, backStack.peek())) {
            return;
        }

        while (!backStack.isEmpty()) {
            Screen topScreen = backStack.peek();

            if (sameScreen(nextScreen, topScreen)) {
                performRouteChange(fromPath, backStack.asList(), direction);
                return;
            }

            backStack.pop();
        }

        backStack.push(nextScreen);
        performRouteChange(fromPath, backStack.asList(), direction);
    }

    public boolean hasActiveScreen() {
        return !backStack.isEmpty();
    }

    private boolean tryHandleEmptyBackstack(final Screen screen) {
        if (backStack.isEmpty()) {
            backStack.push(screen);
            performRouteChange(Collections.<Screen>emptyList(), backStack.asList(), TransitionDirection.ENTER);
            return true;
        }
        return false;
    }

    private void performRouteChange(List<Screen> fromPath, List<Screen> toPath, TransitionDirection direction) {
        onRouteChanged(new RouteChange(fromPath, toPath, direction));
    }

    protected abstract void onRouteChanged(RouteChange routeChange);

    static boolean sameScreen(Screen previous, Screen next) {

        if (previous == null || next == null) {
            return false;
        }
        return previous.equals(next);
    }
}
