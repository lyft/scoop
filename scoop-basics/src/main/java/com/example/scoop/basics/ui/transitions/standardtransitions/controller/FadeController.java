package com.example.scoop.basics.ui.transitions.standardtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.transitions.standardtransitions.screen.HorizontalSlideScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

public class FadeController extends BaseViewController {

    private AppRouter appRouter;

    @Inject
    public FadeController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.fade;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(new HorizontalSlideScreen());
    }

    @Override
    protected Class<? extends ScreenTransition> enterTransition() {
        return FadeTransition.class;
    }

    @Override
    protected Class<? extends ScreenTransition> exitTransition() {
        return FadeTransition.class;
    }
}
