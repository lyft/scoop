package com.lyft.scoop;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class TransitionView extends View {

    public TransitionView(Context context) {
        super(context);
        init();
    }

    public void transition() {
        bringToFront();
        setVisibility(View.VISIBLE);
    }

    public void onTransactionComplete() {
        setVisibility(View.GONE);
    }

    private void init() {
        setBackgroundResource(android.R.color.transparent);
        setClickable(true);
        setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        setVisibility(View.GONE);
    }
}
