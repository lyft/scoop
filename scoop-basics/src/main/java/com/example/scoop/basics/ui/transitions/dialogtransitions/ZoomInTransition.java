package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;

public class ZoomInTransition extends DialogEnterTransition {

    public static final long DEFAULT_SCALE_TIME = 200L;

    @Override
    protected Animator createAnimator(View from) {
        AnimatorSet set = new AnimatorSet();

        set.play(getScaleInAnimator(from, View.SCALE_X)).with(getScaleInAnimator(from, View.SCALE_Y));

        return set;
    }

    private static ObjectAnimator getScaleInAnimator(View view, Property<View, Float> property) {
        return ObjectAnimator.ofFloat(view, property, 0f, 1f).setDuration(DEFAULT_SCALE_TIME);
    }
}

