package com.lyft.scoop;

import android.view.View;

public abstract class ViewController {

    private boolean attached;
    private Scoop scoop;

    public void attach(View view) {}

    protected final boolean attached() {
        return this.attached;
    }

    public void detach(View view) {}

    void setAttached(boolean attached) {
        this.attached = attached;
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
            ViewController viewController = (ViewController) view.getTag(LayoutInflator.VIEW_CONTROLLER_TAG);
            return viewController;
        }

        return null;
    }
}
