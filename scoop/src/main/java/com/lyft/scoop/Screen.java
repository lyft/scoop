package com.lyft.scoop;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;

public class Screen {

    public static final String SERVICE_NAME = "screen";

    private transient SparseArray<Parcelable> viewState;
    private String name;
    private Class<? extends ViewController> viewController;
    private Class<? extends ScreenTransition> enterTransition;
    private Class<? extends ScreenTransition> exitTransition;
    private Class<?> module;
    private Integer layoutId;
    private Object args;

    public Screen() {
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
        screen.name = viewController.getSimpleName();
        return screen;
    }

    public static Screen create(int layoutId, final String name) {
        Screen screen = new Screen();
        screen.layoutId = layoutId;
        screen.name = name;
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

    public <T> Screen module(Class<?> module) {
        this.module= module;
        return this;
    }

    public Class<? extends ScreenTransition> getEnterTransition() {
        return this.enterTransition;
    }

    public Class<? extends ScreenTransition> getExitTransition() {
        return this.exitTransition;
    }

    public Class<? extends ViewController> getController() {
        return viewController;
    }

    public String getName() {
        return name;
    }

    public Class<?> getModule() {
        return module;
    }

    public Integer getLayoutId() {
        return layoutId;
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
