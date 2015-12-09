package com.lyft.scoop;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;

public final class Screen {

    public static final String SERVICE_NAME = "screen";

    private transient SparseArray<Parcelable> viewState;
    private Class<? extends ViewController> viewController;
    private Object args;

    Screen() {
        viewState = new SparseArray<Parcelable>();
    }

    public void saveViewState(View view) {
        SparseArray<Parcelable> viewState = new SparseArray<Parcelable>();
        view.saveHierarchyState(viewState);
        this.viewState = viewState;
    }

    public void restoreViewState(View view) {
        view.restoreHierarchyState(viewState);
    }

    public static Screen create(Class<? extends ViewController> viewController) {
        Screen screen = new Screen();
        screen.viewController = viewController;
        return screen;
    }

    public <T> Screen setArgs(T args) {
        this.args = args;
        return this;
    }

    public Class<? extends ViewController> getController() {
        return viewController;
    }

    public static Screen fromScoop(Scoop scoop) {
        if (scoop == null) {
            return null;
        }

        return scoop.findService("screen");
    }

    public static Screen fromView(View view) {
        return Screen.fromScoop(Scoop.fromView(view));
    }

    public static Screen fromController(ViewController controller) {
        return Screen.fromScoop(controller.getScoop());
    }

    public <T> T args() {
        return (T) args;
    }
}
