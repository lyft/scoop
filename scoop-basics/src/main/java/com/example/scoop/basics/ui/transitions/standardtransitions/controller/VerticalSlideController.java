package com.example.scoop.basics.ui.transitions.standardtransitions.controller;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.BaseViewController;
import com.example.scoop.basics.ui.DemoScreen;
import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;
import javax.inject.Inject;

public class VerticalSlideController extends BaseViewController {

    private AppRouter appRouter;

    @Inject
    public VerticalSlideController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.vertical_slide;
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
    public void onClick() {
        appRouter.resetTo(new DemoScreen());
    }

    @Override
    protected Class<? extends ScreenTransition> enterTransition() {
        return UpwardSlideTransition.class;
    }

    @Override
    protected Class<? extends ScreenTransition> exitTransition() {
        return DownwardSlideTransition.class;
    }
}
