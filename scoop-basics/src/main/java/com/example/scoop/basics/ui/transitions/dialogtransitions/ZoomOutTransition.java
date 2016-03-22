package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;

public class ZoomOutTransition extends DialogExitTransition {

    public static final long DEFAULT_SCALE_TIME = 200L;

    @Override
    protected Animator createAnimator(View from) {
        AnimatorSet set = new AnimatorSet();

        set.play(getScaleOutAnimator(from, View.SCALE_X)).with(getScaleOutAnimator(from, View.SCALE_Y));

        return set;
    }

    private static ObjectAnimator getScaleOutAnimator(View view, Property<View, Float> property) {
        return ObjectAnimator.ofFloat(view, property, 1f, 0f).setDuration(DEFAULT_SCALE_TIME);
    }
}
