package com.example.scoop.basics.ui.navigationsample.controller;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.navigationsample.screen.BScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class AController extends ViewController {

    private AppRouter appRouter;

    @Inject
    public AController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.a;
    }

    @Override
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.go_to_b_button)
    public void goToB() {
        appRouter.goTo(new BScreen());
    }
}
