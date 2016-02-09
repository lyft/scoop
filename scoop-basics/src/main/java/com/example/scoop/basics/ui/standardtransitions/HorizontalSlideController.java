package com.example.scoop.basics.ui.standardtransitions;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

public class HorizontalSlideController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(HorizontalSlideController.class)
                .module(HorizontalSlideController.Module.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(ForwardSlideTransition.class);
    }

    @dagger.Module(
            injects = HorizontalSlideController.class,
            addsTo = FadeController.Module.class,
            library = true
    )
    public static class Module {
    }

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
    public void attach(View view) {
        super.attach(view);

    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }


    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(VerticalSlideController.createScreen());
    }
}
