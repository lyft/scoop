package com.lyft.scoop;

import android.view.View;

public abstract class ViewController {

    private boolean attached;
    private Scoop scoop;
    private View view;

    protected abstract int layoutId();

    public void onAttach() {}

    public void onDetach() {}

    public View getView() {
        if (!attached) {
            throw new IllegalStateException("View accessed while ViewController is detached.");
        }
        return this.view;
    }

    protected final boolean attached() {
        return this.attached;
    }

    protected Scoop getScoop() {
        return scoop;
    }

    void attach(View view) {
        this.attached = true;
        this.view = view;
        onAttach();
    }

    void detach() {
        onDetach();
        this.view = null;
        this.attached = false;
    }

    void setScoop(Scoop scoop) {
        this.scoop = scoop;
    }

    static ViewController fromView(View view) {
        if (view != null) {
            return (ViewController) view.getTag(ViewControllerInflater.VIEW_CONTROLLER_TAG);
        }

        return null;
    }
}
