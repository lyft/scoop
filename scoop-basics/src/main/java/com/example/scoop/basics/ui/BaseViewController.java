package com.example.scoop.basics.ui;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.lyft.scoop.ViewController;

public abstract class BaseViewController extends ViewController {

    private Unbinder unbinder;

    @Override
    public void onAttach() {
        super.onAttach();
        unbinder = ButterKnife.bind(this, getView());
    }

    @Override
    public void onDetach() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }

        super.onDetach();
    }
}
