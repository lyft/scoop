package com.lyft.scoop.transitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.lyft.scoop.TransitionListener;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class VeritcalSlideTransition extends ObjectAnimatorTransition {

    private boolean isUpward;

    public VeritcalSlideTransition(boolean downward) {
        this.isUpward = downward;
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
        int fromTranslation = isUpward ? -from.getHeight() : from.getHeight();
        int toTranslation = isUpward ? to.getHeight() : -to.getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, fromTranslation));
        set.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_Y, toTranslation, 0));

        return set;
    }
}
