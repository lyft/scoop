package com.lyft.scoop;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;

public final class Screen {

    public static final String SERVICE_NAME = "screen";

    private transient SparseArray<Parcelable> viewState;
    private Class<? extends View> view;
    private Class<? extends ScreenTransition> enterTransition;
    private Class<? extends ScreenTransition> exitTransition;
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

    public static Screen create(Class<? extends View> view) {
        Screen screen = new Screen();
        screen.view = view;
        return screen;
    }

    public <T> Screen setArgs(T args) {
        this.args = args;
        return this;
    }

    public <T> Screen enterTransition(Class<? extends ScreenTransition> enterTransition) {
        this.enterTransition = enterTransition;
        return this;
    }

    public <T> Screen exitTransition(Class<? extends ScreenTransition> exitTransition) {
        this.exitTransition = exitTransition;
        return this;
    }

    public Class<? extends View> getView() {
        return view;
    }

    public Class<? extends ScreenTransition> getEnterTransition() {
        return this.enterTransition;
    }

    public Class<? extends ScreenTransition> getExitTransition() {
        return this.exitTransition;
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

    public <T> T args() {
        return (T) args;
    }
}
