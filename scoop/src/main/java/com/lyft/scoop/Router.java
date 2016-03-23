package com.lyft.scoop;

import java.util.Arrays;
import java.util.List;

public abstract class Router {

    private ScoopBackstack backStack = new ScoopBackstack();
    private ScreenScooper screenScooper;
    private Scoop root;
    private boolean allowEmptyStack;

    public Router(ScreenScooper screenScooper) {
        this.screenScooper = screenScooper;
        this.allowEmptyStack = false;
    }

    public Router(ScreenScooper screenScooper, boolean allowEmptyStack) {

        this.screenScooper = screenScooper;
        this.allowEmptyStack = allowEmptyStack;
    }

    public void onCreate(Scoop root) {
        this.root = root;
    }

    protected abstract void onScoopChanged(RouteChange routeChange);

    public boolean goBack() {
        if (!backStack.isEmpty()) {
            Scoop previousScoop = backStack.peek();
            Screen previousScreen = Screen.fromScoop(previousScoop);

            backStack.pop();

            if (!backStack.isEmpty()) {
                Scoop nextScoop = backStack.peek();
                Screen nextScreen = Screen.fromScoop(nextScoop);
                performScoopChange(nextScoop, previousScreen, nextScreen, TransitionDirection.EXIT);
                return true;
            } else if (allowEmptyStack) {
                performScoopChange(previousScoop, previousScreen, null, TransitionDirection.EXIT);
                return true;
            }
        }

        return false;
    }

    public void goTo(Screen screen) {
        if (tryHandleEmptyBackstack(screen)) {
            return;
        }
        Scoop previousScoop = backStack.peek();
        Screen previousScreen = Screen.fromScoop(previousScoop);

        if (sameScreen(Screen.fromScoop(previousScoop), screen)) {
            return;
        }

        Scoop nextScoop = screenScooper.createScreenScoop(screen, previousScoop);
        backStack.push(nextScoop);
        performScoopChange(nextScoop, previousScreen, screen, TransitionDirection.ENTER);
    }

    public void replaceWith(Screen screen) {
        if (tryHandleEmptyBackstack(screen)) {
            return;
        }
        Scoop previousScoop = backStack.peek();
        Screen previousScreen = Screen.fromScoop(previousScoop);

        if (sameScreen(Screen.fromScoop(previousScoop), screen)) {
            return;
        }

        Scoop nextScoop;

        if (!backStack.isEmpty()) {
            Scoop previousParent = previousScoop.getParent();

            backStack.pop();

            nextScoop = screenScooper.createScreenScoop(screen, previousParent);
        } else {
            nextScoop = screenScooper.createScreenScoop(screen, root);
        }

        backStack.push(nextScoop);
        performScoopChange(nextScoop, previousScreen, screen, TransitionDirection.ENTER);
    }

    public void replaceAllWith(Screen... screens) {
        replaceAllWith(Arrays.asList(screens));
    }

    public void replaceAllWith(List<Screen> screens) {
        Screen previousScreen = Screen.fromScoop(backStack.peek());

        backStack.clear();

        Scoop scoop = root;
        for (final Screen screen : screens) {
            final Scoop newScoop = screenScooper.createScreenScoop(screen, scoop);
            backStack.push(newScoop);
            scoop = newScoop;
        }

        Screen currentScreen = null;
        if (!screens.isEmpty()) {
            currentScreen = screens.get(screens.size() - 1);
        }
        performScoopChange(backStack.peek(), previousScreen, currentScreen, TransitionDirection.ENTER);
    }

    public void resetTo(Screen screen) {
        if (tryHandleEmptyBackstack(screen)) {
            return;
        }
        resetTo(screen, TransitionDirection.EXIT);
    }

    public void resetTo(Screen screen, TransitionDirection direction) {
        Scoop previousScoop = backStack.peek();
        Screen previousScreen = Screen.fromScoop(previousScoop);

        while (!backStack.isEmpty()) {
            Scoop topScoop = backStack.peek();

            if (sameScreen(screen, Screen.fromScoop(topScoop))) {
                performScoopChange(topScoop, previousScreen, screen, direction);
                return;
            }

            backStack.pop();
        }

        Scoop nextScoop = screenScooper.createScreenScoop(screen, root);
        backStack.push(nextScoop);
        performScoopChange(nextScoop, previousScreen, screen, direction);
    }

    public boolean hasActiveScreen() {
        return !backStack.isEmpty();
    }

    private boolean tryHandleEmptyBackstack(final Screen screen) {
        if (backStack.isEmpty()) {
            final Scoop newScoop = screenScooper.createScreenScoop(screen, root);
            backStack.push(newScoop);
            performScoopChange(backStack.peek(), null, screen, TransitionDirection.ENTER);
            return true;
        }
        return false;
    }

    private void performScoopChange(Scoop scoop, Screen previous, Screen next, TransitionDirection direction) {
        onScoopChanged(new RouteChange(scoop, previous, next, direction));
    }

    static boolean sameScreen(Screen previous, Screen next) {

        if (previous == null || next == null) {
            return false;
        }
        return previous.equals(next);
    }
}
