package com.example.scoop.basics.ui.navigationsample.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.navigationsample.screen.CScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

public class BController extends BaseViewController {

    private AppRouter appRouter;

    @Inject
    public BController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.b;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @OnClick(R.id.go_back_button)
    public void goBack() {
        appRouter.goBack();
    }

    @OnClick(R.id.go_to_c_button)
    public void goToB() {
        appRouter.goTo(new CScreen());
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
