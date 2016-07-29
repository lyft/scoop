package com.example.scoop.basics.ui.layoutsample.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.layoutsample.LayoutInjectData;
import com.example.scoop.basics.ui.layoutsample.screen.LayoutScreen;
import com.lyft.scoop.Screen;
import javax.inject.Inject;

public class LayoutView extends FrameLayout {

    @Inject
    AppRouter appRouter;

    @Inject
    LayoutInjectData layoutInjectData;

    @BindView(R.id.inject_text_view)
    TextView injectTextView;

    @BindView(R.id.param_text_view)
    TextView paramTextView;

    public LayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isInEditMode()) {
            return;
        }

        ButterKnife.bind(this, this);
        final LayoutScreen layoutScreen = Screen.fromView(this);
        paramTextView.setText(layoutScreen.getParam());
        injectTextView.setText(layoutInjectData.getData());
    }

    @OnClick(R.id.go_back_button)
    public void goBack() {
        appRouter.goBack();
    }
}
