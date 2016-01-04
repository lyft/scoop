package com.example.scoop.basics.ui.navigationsample;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

//@InjectThat(AController.Module.class)
@EnterTransition(FadeTransition.class)
@ExitTransition(FadeTransition.class)
public class AController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(AController.class);
    }

    @dagger.Module(
    )
    public static class Module {}

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
        appRouter.goTo(BController.createScreen());
    }
}
