package com.example.scoop.basics.ui.standardtransitions.controller;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.DemoScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class VerticalSlideController extends ViewController {
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
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);

    }

    @OnClick(R.id.next_button)
    public void onClick() {
        appRouter.resetTo(new DemoScreen());
    }
}
