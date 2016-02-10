package com.example.scoop.basics.ui.paramsample;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Bind;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.FadeTransition;

@ControllerModule(ParametrizedController.Module.class)
@Layout(R.layout.parametrized)
public class ParametrizedController extends FrameLayout {

    public ParametrizedController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen(String param) {
        return Screen.create(ParametrizedController.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class)
                .setArgs(param);
    }

    @dagger.Module(
            injects = ParametrizedController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module {}

    @Bind(R.id.param_text_view)
    TextView paramTextView;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        String args = Screen.fromView(this).args();
        paramTextView.setText(args);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
