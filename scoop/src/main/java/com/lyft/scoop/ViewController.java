package com.lyft.scoop;

import android.view.View;
import com.lyft.scoop.transitions.InstantTransition;

public abstract class ViewController {

    private static final int VIEW_CONTROLLER_TAG = 0x80000001;

    private boolean isDetaching = false;
    private boolean attached;
    private Scoop scoop;
    private View view;

    final void attach(View view) {
        this.view = view;
        view.setTag(VIEW_CONTROLLER_TAG, this);
        onAttach();
        this.attached = true;
        if (this.isDetaching) {
            detach(view);
        }
    }

    public void onAttach() {}

    protected final boolean attached() {
        return this.attached;
    }

    final void detach(View view) {
        this.isDetaching = true;
        if (this.attached) {
            onDetach();
            view.setTag(VIEW_CONTROLLER_TAG, null);
            this.view = null;
            this.attached = false;
            this.isDetaching = false;
        }
    }

    public void onDetach() {}

    public View getView() {
        if (view == null) {
            throw new IllegalStateException("View accessed while ViewController is detached.");
        }
        return this.view;
    }

    protected abstract int layoutId();

    protected Scoop getScoop() {
        return scoop;
    }

    void setScoop(Scoop scoop) {
        this.scoop = scoop;
    }

    static ViewController fromView(View view) {
        if (view != null) {
            ViewController viewController = (ViewController) view.getTag(VIEW_CONTROLLER_TAG);
            return viewController;
        }

        return null;
    }

    protected ScreenTransition enterTransition() {
        return new InstantTransition();
    }

    protected ScreenTransition exitTransition() {
        return new InstantTransition();
    }
}
