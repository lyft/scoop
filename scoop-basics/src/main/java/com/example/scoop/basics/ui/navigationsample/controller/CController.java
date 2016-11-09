package com.example.scoop.basics.ui.navigationsample.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.navigationsample.screen.AScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class CController extends BaseViewController {

    private AppRouter appRouter;

    @Inject
    public CController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.c;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.reset_to_a_button)
    public void onClick() {
        appRouter.resetTo(new AScreen());
    }

    @Override
    protected ScreenTransition enterTransition() {
        return new ForwardSlideTransition();
    }

    @Override
    protected ScreenTransition exitTransition() {
        return new BackwardSlideTransition();
    }
}
