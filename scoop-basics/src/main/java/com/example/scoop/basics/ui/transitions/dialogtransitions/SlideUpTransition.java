package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class SlideUpTransition extends DialogEnterTransition {

    @Override
    protected Animator createAnimator(View to) {
        int toTranslation = to.getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_Y, toTranslation, 0));

        return set;
    }
}
