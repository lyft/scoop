package com.example.scoop.basics.ui.standardtransitions;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.ParentController;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

//@InjectThat(HorizontalSlideController.Module.class)
@ParentController(FadeController.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class HorizontalSlideController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(HorizontalSlideController.class);
    }

    @dagger.Module(
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
