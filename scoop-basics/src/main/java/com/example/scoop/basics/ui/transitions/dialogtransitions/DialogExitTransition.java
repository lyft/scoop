package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.TransitionListener;

public abstract class DialogExitTransition implements ScreenTransition {

    @Override
    public void transition(final ViewGroup root, final View from, final View to, final TransitionListener transitionListener) {
        Animator animator = createAnimator(from);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ((ViewGroup) from.getParent()).removeView(from);
                transitionListener.onTransitionCompleted();
            }
        });
        animator.start();
    }

    protected abstract Animator createAnimator(View from);
}
