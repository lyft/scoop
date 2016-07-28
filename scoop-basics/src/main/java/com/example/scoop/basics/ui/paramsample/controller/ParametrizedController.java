package com.example.scoop.basics.ui.paramsample.controller;

import android.widget.TextView;
import butterknife.BindView;
import com.example.scoop.basics.R;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.paramsample.screen.ParametrizedScreen;
import com.lyft.scoop.Screen;
import javax.inject.Inject;

public class ParametrizedController extends BaseViewController {

    @BindView(R.id.param_text_view)
    TextView paramTextView;

    @Inject
    public ParametrizedController() {
    }

    @Override
    protected int layoutId() {
        return R.layout.parametrized;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        ParametrizedScreen screen = (ParametrizedScreen) Screen.fromController(this);
        paramTextView.setText(screen.getArgs());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
