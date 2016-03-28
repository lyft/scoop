package com.lyft.scoop;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;

public class Screen {

    public static final String SERVICE_NAME = "screen";

    private transient SparseArray<Parcelable> viewState;

    public Screen() {
        viewState = new SparseArray<Parcelable>();
    }

    public static boolean equals(Screen previous, Screen next) {

        if (previous == null || next == null) {
            return false;
        }
        return previous.equals(next);
    }

    public void saveViewState(View view) {
        SparseArray<Parcelable> viewState = new SparseArray<Parcelable>();
        view.saveHierarchyState(viewState);
        this.viewState = viewState;
    }

    public void restoreViewState(View view) {
        view.restoreHierarchyState(viewState);
    }

    public Class<? extends ViewController> getController() {
        Controller controller = getClass().getAnnotation(Controller.class);
        if (controller != null) {
            return controller.value();
        }
        return null;
    }

    public Integer getLayout() {
        Layout layout = getClass().getAnnotation(Layout.class);
        if (layout != null) {
            return layout.value();
        }
        return null;
    }

    public static <T extends Screen> T fromScoop(Scoop scoop) {
        if (scoop == null) {
            return null;
        }

        return scoop.findService("screen");
    }

    public static <T extends Screen> T fromView(View view) {
        return Screen.fromScoop(Scoop.fromView(view));
    }

    public static <T extends Screen> T fromController(ViewController controller) {
        return Screen.fromScoop(controller.getScoop());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Screen)) {
            return false;
        }

        Screen screen = (Screen) o;
        return equals(screen.getClass(), getClass());
    }

    public boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
