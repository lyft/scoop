package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideDownTransition extends DialogExitTransition {

    @Override
    protected Animator createAnimator(View from) {
        int fromTranslation = from.getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, fromTranslation));

        return set;
    }
}
