package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.TransitionListener;

public class SlideDownTransition implements ScreenTransition {

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

    private Animator createAnimator(View from) {
        int fromTranslation = from.getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, fromTranslation));

        return set;
    }
}
