package com.example.scoop.basics.ui.layoutsample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DaggerInjector;
import javax.inject.Inject;

public class LayoutView extends LinearLayout {

    @Inject
    AppRouter appRouter;

    public LayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        DaggerInjector.fromView(this).inject(this);
    }

    @OnClick(R.id.layout_sample_button)
    public void goToLayoutSample() {
        appRouter.goBack();
    }
}
