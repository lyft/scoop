package com.example.scoop.basics.ui.customtransition;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class AutoTransitionEndController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(AutoTransitionEndController.class)
                .module(AutoTransitionEndController.Module.class)
                .enterTransition(AutoTransition.class)
                .exitTransition(AutoTransition.class);

    }

    @dagger.Module(
            injects = AutoTransitionEndController.class,
            addsTo = AutoTransitionStartController.Module.class,
            library = true
    )
    public static class Module { }

    private AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Inject
    public AutoTransitionEndController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.auto_transition_end;
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

    @OnClick(R.id.back_button)
    public void goNext() {
        appRouter.goBack();
    }
}
