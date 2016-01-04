package com.example.scoop.basics.ui.customtransition;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

//@InjectThat(AutoTransitionStartController.Module.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class AutoTransitionStartController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(AutoTransitionStartController.class);
    }

    @dagger.Module(
    )
    public static class Module {}

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
