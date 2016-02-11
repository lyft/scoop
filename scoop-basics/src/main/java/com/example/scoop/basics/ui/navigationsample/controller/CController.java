package com.example.scoop.basics.ui.navigationsample.controller;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.navigationsample.screen.AScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class CController extends ViewController {

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
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.reset_to_a_button)
    public void onClick() {
        appRouter.resetTo(new AScreen());
    }
}
