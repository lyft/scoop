package com.lyft.scoop.transitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.lyft.scoop.TransitionListener;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
public class FadeTransition extends ObjectAnimatorTransition {

    public static final long DEFAULT_FADE_TIME = 250L;

    private final long fadeTime;
    private final Interpolator interpolator;

    public FadeTransition() {
        this.fadeTime = DEFAULT_FADE_TIME;
        this.interpolator = new LinearInterpolator();
    }

    public FadeTransition(final long fadeTime, final Interpolator interpolator) {
        this.fadeTime = fadeTime;
        this.interpolator = interpolator;
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
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(from, View.ALPHA, 1f, 0f).setDuration(fadeTime);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(to, View.ALPHA, 0f, 1f).setDuration(fadeTime);

        fadeOut.setInterpolator(interpolator);
        fadeIn.setInterpolator(interpolator);

        set.play(fadeOut);
        set.play(fadeIn);

        return set;
    }
}
