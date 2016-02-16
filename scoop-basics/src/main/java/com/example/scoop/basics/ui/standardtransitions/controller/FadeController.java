package com.example.scoop.basics.ui.standardtransitions.controller;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.standardtransitions.screen.HorizontalSlideScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class FadeController extends ViewController {
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
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(new HorizontalSlideScreen());
    }
}
