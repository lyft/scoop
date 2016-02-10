package com.example.scoop.basics.ui.navigationsample;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class CController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(CController.class)
                .module(CController.Module.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(BackwardSlideTransition.class);
    }

    @dagger.Module(
            injects = CController.class,
            addsTo = BController.Module.class,
            library = true
    )
    public static class Module { }

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
        appRouter.resetTo(AController.createScreen());
    }
}
