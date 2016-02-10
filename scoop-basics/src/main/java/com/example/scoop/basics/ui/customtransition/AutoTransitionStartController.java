package com.example.scoop.basics.ui.customtransition;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

public class AutoTransitionStartController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(AutoTransitionStartController.class)
                .module(AutoTransitionStartController.Module.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class);
    }

    @dagger.Module(
            injects = AutoTransitionStartController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module { }

    private AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Inject
    public AutoTransitionStartController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.auto_transition_start;
    }

    @Override
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);

        viewSubscriptions.unsubscribe();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(AutoTransitionEndController.createScreen());
    }
}
