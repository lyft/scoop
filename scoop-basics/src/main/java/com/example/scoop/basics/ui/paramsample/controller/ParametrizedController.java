package com.example.scoop.basics.ui.paramsample.controller;

import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import com.example.scoop.basics.R;
import com.example.scoop.basics.ui.paramsample.screen.ParametrizedScreen;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class ParametrizedController extends ViewController {

    @Bind(R.id.param_text_view)
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
