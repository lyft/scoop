package com.example.scoop.basics.ui.navigationsample;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

@ControllerModule(BController.Module.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class BController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(BController.class);
    }

    @dagger.Module(
            injects = BController.class,
            addsTo = AController.Module.class,
            library = true
    )
    public static class Module {
    }

    private AppRouter appRouter;

    @Inject
    public BController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.b;
    }

    @Override
    public void attach(View view) {
        super.attach(view);

    }

    @Override
    public void detach(View view) {
        super.detach(view);
    }

    @OnClick(R.id.go_back_button)
    public void goBack() {
        appRouter.goBack();
    }

    @OnClick(R.id.go_to_c_button)
    public void goToB() {
        appRouter.goTo(CController.createScreen());
    }
}
