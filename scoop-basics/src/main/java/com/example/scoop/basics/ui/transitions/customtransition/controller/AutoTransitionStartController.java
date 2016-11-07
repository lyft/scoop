package com.example.scoop.basics.ui.transitions.customtransition.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.transitions.customtransition.screen.AutoTransitionEndScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

public class AutoTransitionStartController extends BaseViewController {

    private AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Inject
    public AutoTransitionStartController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.auto_transition_start;
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

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(new AutoTransitionEndScreen());
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
