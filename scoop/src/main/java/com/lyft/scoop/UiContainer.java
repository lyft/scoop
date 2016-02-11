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

    protected ViewControllerInflater getViewControllerInflater() {
        return new ViewControllerInflater();
    }

    protected LayoutInflater getLayoutInflater() {
        return new LayoutInflater();
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
        if (nextScreen.getController() != null) {
            active = inflateControllerView(routeChange, nextScreen);
        } else {
            active = inflateLayout(routeChange, nextScreen);
        }

        nextScreen.restoreViewState(active);

        currentScreen = nextScreen;

        getTransitionView().transition();
        final ScreenTransition transition = getTransition(routeChange);
        transition.transition(this, prevView, active, this);
    }

    private View inflateControllerView(RouteChange screenChange, Screen nextScreen) {
        return getViewControllerInflater().inflateViewController(screenChange.scoop, nextScreen.getController(), this);
    }

    private View inflateLayout(RouteChange screenChange, Screen nextScreen) {
        return getLayoutInflater().inflateView(screenChange.scoop, nextScreen, this);
    }

    private ScreenTransition getTransition(RouteChange screenChange) {
        if (screenChange.direction == TransitionDirection.ENTER) {
            return getEnterTransition(screenChange);
        } else {
            return getExitTransition(screenChange);
        }
    }

    private boolean childCanGoBack() {
        if (active instanceof HandleBack) {
            return handleBack(active);
        }
        ViewController viewController = ViewController.fromView(active);

        if (viewController != null) {
            if (viewController instanceof HandleBack) {
                return handleBack(viewController);
            }
        }

        return false;
    }

    private boolean handleBack(final Object object) {
        HandleBack handleBack = (HandleBack) object;

        return handleBack.onBack();
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
        EnterTransition enterTransition = screenChange.next.getClass().getAnnotation(EnterTransition.class);

        if (enterTransition != null && screenChange.previous != null) {
            try {
                return enterTransition.value().newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Failed to instantiate enter transition: " + enterTransition.value().getSimpleName(), e);
            }
        }

        return new InstantTransition();
    }

    static ScreenTransition getExitTransition(RouteChange screenChange) {
        ExitTransition exitTransition = screenChange.previous.getClass().getAnnotation(ExitTransition.class);

        if (exitTransition != null && screenChange.next != null) {
            try {
                return exitTransition.value().newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Failed to instantiate exit transition: " + exitTransition.value().getSimpleName(), e);
            }
        }

        return new InstantTransition();
    }
}
