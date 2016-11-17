package com.lyft.scoop;

import android.content.Context;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.lyft.scoop.transitions.InstantTransition;
import java.util.ArrayDeque;

public abstract class UiContainer extends FrameLayout implements HandleBack, TransitionListener {

    private ArrayDeque<RouteChange> screenSwapQueue = new ArrayDeque<>();
    private boolean isTransitioning;
    private View active;

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

    protected ScreenScooper getScreenScooper() {
        return new ScreenScooper(new ScreenScoopFactory());
    }

    public boolean onBack() {
        return childCanGoBack();
    }

    public void goTo(RouteChange routeChange) {
        if (screenSwapQueue.isEmpty()) {
            screenSwapQueue.add(routeChange);

            swap(routeChange);
        } else {
            screenSwapQueue.add(routeChange);
        }
    }

    @Override
    public void onTransitionCompleted() {
        final TransitionListener transitionListener = getTransitionListener();
        transitionListener.onTransitionCompleted();
        isTransitioning = false;

        if (!screenSwapQueue.isEmpty()) {
            screenSwapQueue.pop();

            if (!screenSwapQueue.isEmpty()) {
                swap(screenSwapQueue.peek());
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isTransitioning) {
            return true;
        } else {
            return super.dispatchTouchEvent(event);
        }
    }

    @Override
    public boolean dispatchDragEvent(DragEvent event) {
        if (isTransitioning) {
            return true;
        } else {
            return super.dispatchDragEvent(event);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (isTransitioning) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if (isTransitioning) {
            return true;
        } else {
            return super.dispatchKeyEventPreIme(event);
        }
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        if (isTransitioning) {
            return true;
        } else {
            return super.dispatchKeyShortcutEvent(event);
        }
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        if (isTransitioning) {
            return true;
        } else {
            return super.dispatchTrackballEvent(event);
        }
    }

    private void swap(RouteChange routeChange) {
        final Scoop currentScoop = getActiveScoop(routeChange);
        final ScreenSwap screenSwap = routeChange.toScreenSwap();
        Screen previousScreen = screenSwap.previous;
        Screen nextScreen = screenSwap.next;
        final View prevView = active;
        ViewController nextViewController = null;

        if (active != null && previousScreen != null) {
            previousScreen.saveViewState(active);
        }

        if (nextScreen == null) {
            active = null;
        } else if (nextScreen.getController() != null) {
            nextViewController = getViewControllerInflater().inflateViewController(currentScoop, nextScreen.getController(), this);
            active = inflateViewFromController(nextViewController, currentScoop);
            nextScreen.restoreViewState(active);
        } else {
            active = inflateLayout(nextScreen, currentScoop);
            nextScreen.restoreViewState(active);
        }

        isTransitioning = true;
        ScreenTransition transition;
        if (screenSwap.direction == TransitionDirection.ENTER) {
            transition = getTransition(nextViewController, screenSwap.direction);
        } else {
            transition = getTransition(ViewController.fromView(prevView), screenSwap.direction);
        }
        transition.transition(this, prevView, active, this);
    }

    private View inflateViewFromController(ViewController viewController, final Scoop scoop) {
        View view = scoop.inflate(viewController.layoutId(), this, false);
        ViewControllerInflater.bindViewControllerToView(view, viewController);
        return view;
    }

    private View inflateLayout(Screen nextScreen, final Scoop scoop) {
        return getLayoutInflater().inflateView(scoop, nextScreen, this);
    }

    static ScreenTransition getTransition(ViewController viewController, TransitionDirection transitionDirection) {
        if (viewController == null) {
            return new InstantTransition();
        }

        if (transitionDirection == TransitionDirection.ENTER) {
            return viewController.enterTransition();
        } else {
            return viewController.exitTransition();
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

    private Scoop getActiveScoop(final RouteChange routeChange) {
        Scoop rootScoop = Scoop.fromView(this);

        Scoop currentScreenScoop = Scoop.fromView(active);

        return getScreenScooper().create(rootScoop, currentScreenScoop, routeChange.fromPath, routeChange.toPath);
    }

    private TransitionListener getTransitionListener() {
        ViewController controller = ViewController.fromView(active);

        TransitionListener transitionListener = new NoOpTransitionListener();

        if (controller instanceof TransitionListener) {
            transitionListener = (TransitionListener) controller;
        }

        return transitionListener;
    }
}
