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
        if (to == null) {
            root.removeView(from);
            return;
        }

        Animator animator = createAnimator(to);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                root.removeView(from);
                transitionListener.onTransitionCompleted();
            }
        });
        animator.start();
    }

    private Animator createAnimator(View to) {
        int fromTranslation = to.getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_Y, fromTranslation));

        return set;
    }
}
