package com.example.scoop.basics.ui.transitions.standardtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.transitions.standardtransitions.screen.VerticalSlideScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class HorizontalSlideController extends BaseViewController {

    private AppRouter appRouter;

    @Inject
    public HorizontalSlideController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.horizontal_slide;
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
        appRouter.goTo(new VerticalSlideScreen());
    }

    @Override
    protected Class<? extends ScreenTransition> enterTransition() {
        return ForwardSlideTransition.class;
    }

    @Override
    protected Class<? extends ScreenTransition> exitTransition() {
        return BackwardSlideTransition.class;
    }
}
