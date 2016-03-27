package com.example.scoop.basics.ui.transitions.customtransition;

import android.annotation.TargetApi;
import android.os.Build;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.transitions.ScreenTransition;
import com.lyft.scoop.TransitionListener;

public class AutoTransition implements ScreenTransition {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void transition(final ViewGroup root, final View from, final View to, final TransitionListener transitionListener) {

        Scene toScene = new Scene(root, to);

        android.transition.AutoTransition transition = new android.transition.AutoTransition();

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transitionListener.onTransitionCompleted();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                transitionListener.onTransitionCompleted();
            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        TransitionManager.go(toScene, transition);
    }
}
