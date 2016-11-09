package com.example.scoop.basics.ui.transitions.customtransition.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.transitions.customtransition.AutoTransition;
import com.lyft.scoop.ScreenTransition;
import javax.inject.Inject;

public class AutoTransitionEndController extends BaseViewController {

    private AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Inject
    public AutoTransitionEndController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.auto_transition_end;
    }

    @Override
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        viewSubscriptions.unsubscribe();
    }

    @OnClick(R.id.back_button)
    public void goNext() {
        appRouter.goBack();
    }

    @Override
    protected ScreenTransition enterTransition() {
        return new AutoTransition();
    }

    @Override
    protected ScreenTransition exitTransition() {
        return new AutoTransition();
    }
}
