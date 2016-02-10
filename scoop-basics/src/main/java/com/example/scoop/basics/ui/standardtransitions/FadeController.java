package com.example.scoop.basics.ui.standardtransitions;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

public class FadeController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(FadeController.class)
                .module(FadeController.Module.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class);
    }

    @dagger.Module(
            injects = FadeController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module { }

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
        appRouter.goTo(HorizontalSlideController.createScreen());
    }
}
