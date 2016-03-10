package com.example.scoop.basics.ui.standardtransitions.controller;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.standardtransitions.screen.VerticalSlideScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class HorizontalSlideController extends ViewController {
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
}
