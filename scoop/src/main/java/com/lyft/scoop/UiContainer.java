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

    private ArrayDeque<ScreenSwap> screenSwapQueue = new ArrayDeque<>();
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

    public boolean onBack() {
        return childCanGoBack();
    }

    public View getActiveView() {
        return active;
    }

    public void goTo(ScreenSwap screenSwap) {
        if (screenSwapQueue.isEmpty()) {
            screenSwapQueue.add(screenSwap);

            swap(screenSwap);
        } else {
            screenSwapQueue.add(screenSwap);
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

    private void swap(ScreenSwap screenSwap) {
        Screen nextScreen = screenSwap.next;
        final View prevView = active;

        if (active != null && screenSwap.previous != null) {
            screenSwap.previous.saveViewState(active);
        }

        if (nextScreen == null) {
            active = null;
        } else if (nextScreen.getController() != null) {
            active = inflateControllerView(screenSwap, nextScreen);
            nextScreen.restoreViewState(active);
        } else {
            active = inflateLayout(screenSwap, nextScreen);
            nextScreen.restoreViewState(active);
        }

        isTransitioning = true;
        final ScreenTransition transition = getTransition(screenSwap);
        transition.transition(this, prevView, active, this);
    }

    private View inflateControllerView(ScreenSwap screenChange, Screen nextScreen) {
        return getViewControllerInflater().inflateViewController(screenChange.scoop, nextScreen.getController(), this);
    }

    private View inflateLayout(ScreenSwap screenChange, Screen nextScreen) {
        return getLayoutInflater().inflateView(screenChange.scoop, nextScreen, this);
    }

    private ScreenTransition getTransition(ScreenSwap screenChange) {
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

    static ScreenTransition getEnterTransition(ScreenSwap screenChange) {
        if (screenChange.next == null) {
            return new InstantTransition();
        }

        EnterTransition enterTransition = screenChange.next.getClass().getAnnotation(EnterTransition.class);

        if (enterTransition != null) {
            try {
                return enterTransition.value().newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Failed to instantiate enter transition: " + enterTransition.value().getSimpleName(), e);
            }
        }

        return new InstantTransition();
    }

    static ScreenTransition getExitTransition(ScreenSwap screenChange) {
        if (screenChange.previous == null) {
            return new InstantTransition();
        }

        ExitTransition exitTransition = screenChange.previous.getClass().getAnnotation(ExitTransition.class);

        if (exitTransition != null) {
            try {
                return exitTransition.value().newInstance();
            } catch (Throwable e) {
                throw new RuntimeException("Failed to instantiate exit transition: " + exitTransition.value().getSimpleName(), e);
            }
        }

        return new InstantTransition();
    }
}
