package com.lyft.scoop.transitions.basic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.TransitionListener;
import com.lyft.scoop.transitions.ObjectAnimatorTransition;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class HorizontalSlideTransition extends ObjectAnimatorTransition {

    private boolean forward;

    public HorizontalSlideTransition(boolean forward) {
        this.forward = forward;
    }

    @Override
    public void performTranslate(final ViewGroup root, final View from, View to, final TransitionListener transitionListener) {
        Animator animator = createAnimator(from, to);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                root.removeView(from);
                transitionListener.onTransitionCompleted();
            }
        });
        animator.start();
    }

    private Animator createAnimator(View from, View to) {
        int fromTranslation = forward ? -from.getWidth() : from.getWidth();
        int toTranslation = forward ? to.getWidth() : -to.getWidth();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_X, fromTranslation));
        set.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_X, toTranslation, 0));

        return set;
    }
}
