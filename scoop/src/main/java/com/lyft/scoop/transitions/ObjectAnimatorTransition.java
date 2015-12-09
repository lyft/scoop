package com.lyft.scoop.transitions;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.TransitionListener;

public abstract class ObjectAnimatorTransition implements ScreenTransition {

    @Override
    public void transition(final ViewGroup root, final View from, final View to, final TransitionListener transitionListener) {
        root.addView(to);
        waitForMeasure(to, new OnMeasuredCallback() {
            @Override
            public void onMeasured(View view, int width, int height) {
                performTranslate(root, from, to, transitionListener);
            }
        });
    }

    protected abstract void performTranslate(ViewGroup root, View from, View to, TransitionListener transitionListener);

    private interface OnMeasuredCallback {

        void onMeasured(View view, int width, int height);
    }

    private static void waitForMeasure(final View view, final OnMeasuredCallback callback) {
        int width = view.getWidth();
        int height = view.getHeight();

        if (width > 0 && height > 0) {
            callback.onMeasured(view, width, height);
            return;
        }

        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                final ViewTreeObserver observer = view.getViewTreeObserver();
                if (observer.isAlive()) {
                    observer.removeOnPreDrawListener(this);
                }

                callback.onMeasured(view, view.getWidth(), view.getHeight());

                return true;
            }
        });
    }
}
