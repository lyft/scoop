package com.lyft.scoop.transitions;

import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.TransitionListener;

public class InstantTransition implements ScreenTransition {

    @Override
    public void transition(ViewGroup root, View from, View to, final TransitionListener transitionListener) {
        root.removeView(from);
        if (to == null) {
            transitionListener.onTransitionCompleted();
            return;
        }

        to.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                transitionListener.onTransitionCompleted();
                v.removeOnAttachStateChangeListener(this);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {

            }
        });

        root.addView(to);
    }
}