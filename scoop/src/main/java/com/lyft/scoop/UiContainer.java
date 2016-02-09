package com.lyft.scoop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.lyft.scoop.transitions.InstantTransition;
import java.util.ArrayDeque;

public abstract class UiContainer extends FrameLayout implements HandleBack, TransitionListener {

    private TransitionView transitionView;
    private ArrayDeque<RouteChange> routeChangeQueue = new ArrayDeque<>();
    private View active;
    private Screen currentScreen;

    public UiContainer(Context context) {
        this(context, null, 0);
    }

    public UiContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UiContainer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) {
            return;
        }
    }

    protected ScoopInflater getViewControllerInflater() {
        return new ScoopInflater();
    }

    public boolean onBack() {
        return childCanGoBack();
    }

    public void goTo(RouteChange routeChange) {
        if (routeChangeQueue.isEmpty()) {
            routeChangeQueue.add(routeChange);

            swap(routeChange);
        } else {
            routeChangeQueue.add(routeChange);
        }
    }

    @Override
    public void onTransitionCompleted() {
        final TransitionListener transitionListener = getTransitionListener();
        transitionListener.onTransitionCompleted();
        getTransitionView().onTransactionComplete();

        if (!routeChangeQueue.isEmpty()) {
            routeChangeQueue.pop();

            if (!routeChangeQueue.isEmpty()) {
                swap(routeChangeQueue.peek());
            }
        }
    }

    private void swap(RouteChange routeChange) {
        Screen nextScreen = routeChange.next;

        final View prevView = active;

        if (active != null) {
            currentScreen.saveViewState(active);
        }

        active = inflateControllerView(routeChange, nextScreen);

        nextScreen.restoreViewState(active);

        currentScreen = nextScreen;

        getTransitionView().transition();
        final ScreenTransition transition = getTransition(routeChange);
        transition.transition(this, prevView, active, this);
    }

    private View inflateControllerView(RouteChange screenChange, Screen nextScreen) {
        return getViewControllerInflater().inflateViewController(screenChange.scoop, nextScreen, this);
    }

    private ScreenTransition getTransition(RouteChange screenChange) {
        if (screenChange.direction == TransitionDirection.ENTER) {
            return getEnterTransition(screenChange);
        } else {
            return getExitTransition(screenChange);
        }
    }

    private boolean childCanGoBack() {
        ViewController viewController = ViewController.fromView(active);

        if (viewController != null) {
            if (viewController instanceof HandleBack) {
                HandleBack handleBack = (HandleBack) viewController;

                return handleBack.onBack();
            }
        }

        return false;
    }

    private TransitionListener getTransitionListener() {
        ViewController controller = ViewController.fromView(active);

        TransitionListener transitionListener = new NoOpTransitionListener();

        if (controller instanceof TransitionListener) {
            transitionListener = (TransitionListener) controller;
        }

        return transitionListener;
    }

    private TransitionView getTransitionView() {
        if (transitionView != null) {
            return transitionView;
        }
        transitionView = new TransitionView(getContext());
        addView(transitionView);
        return transitionView;
    }

    static ScreenTransition getEnterTransition(RouteChange screenChange) {
        Class<? extends ScreenTransition> enterTransition = screenChange.next.getEnterTransition();

        if (enterTransition != null && screenChange.previous != null) {
            try {
                return enterTransition.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Failed to instantiate enter transition: " + enterTransition.getSimpleName(), e);
            }
        }

        return new InstantTransition();
    }

    static ScreenTransition getExitTransition(RouteChange screenChange) {
        Class<? extends ScreenTransition> exitTransition = screenChange.previous.getExitTransition();

        if (exitTransition != null && screenChange.next != null) {
            try {
                return exitTransition.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Failed to instantiate exit transition: " + exitTransition.getSimpleName(), e);
            }
        }

        return new InstantTransition();
    }
}
