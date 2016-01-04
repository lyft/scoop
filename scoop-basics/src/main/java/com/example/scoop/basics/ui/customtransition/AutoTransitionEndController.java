package com.example.scoop.basics.ui.customtransition;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.InjectThat;
import com.example.scoop.basics.ui.DemosControllerInjector;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

@InjectThat(DemosControllerInjector.class)
@EnterTransition(AutoTransition.class)
@ExitTransition(AutoTransition.class)
public class AutoTransitionEndController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(AutoTransitionEndController.class);
    }

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
