package com.example.scoop.basics.ui.transitions.dialogtransitions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import com.example.scoop.basics.R;
import com.example.scoop.basics.util.BitmapUtils;
import com.lyft.scoop.TransitionListener;
import com.lyft.scoop.transitions.ObjectAnimatorTransition;

public class SlideUpTransition extends ObjectAnimatorTransition {

    @Override
    public void transition(final ViewGroup root, final View from, final View to, final TransitionListener transitionListener) {
        if (from != null) {
            // this needs to be done before "to" is added to root
            setPreviousViewAsBackground(from, to);
        }
        super.transition(root, from, to, transitionListener);
    }

    private void setPreviousViewAsBackground(View from, View to) {
        if (from.getBackground() == null) {
            from.setBackgroundColor(from.getResources().getColor(android.R.color.white));
        }
        final BitmapDrawable screenshotDrawable = getScreenshotDrawable(from);
        to.setBackground(screenshotDrawable);
    }

    private static BitmapDrawable getScreenshotDrawable(View view) {
        view.setDrawingCacheEnabled(true);
        final Bitmap screenBitmap = BitmapUtils.fastblur(view.getDrawingCache(), 0.1f, 20);
        final BitmapDrawable bitmapDrawable = new BitmapDrawable(view.getResources(), screenBitmap);
        view.setDrawingCacheEnabled(false);

        return bitmapDrawable;
    }

    @Override
    public void performTranslate(final ViewGroup root, final View from, final View to, final TransitionListener transitionListener) {
        if (to == null) {
            root.removeView(from);
            return;
        }

        final View viewToAnimate = to.findViewById(R.id.dialog);

        Animator animator = createAnimator(viewToAnimate);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                root.removeView(from);
                transitionListener.onTransitionCompleted();
            }
        });
        animator.start();
    }

    private Animator createAnimator(View from) {
        int toTranslation = from.getRootView().getHeight();

        AnimatorSet set = new AnimatorSet();

        set.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, toTranslation, 0));

        return set;
    }
}
