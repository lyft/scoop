package com.example.scoop.basics.ui.standardtransitions;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;
import javax.inject.Inject;

//@InjectThat(VerticalSlideController.Module.class)
@EnterTransition(UpwardSlideTransition.class)
@ExitTransition(DownwardSlideTransition.class)
public class VerticalSlideController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(VerticalSlideController.class);
    }

    @dagger.Module(
    )
    public static class Module {}

    private AppRouter appRouter;

    @Inject
    public VerticalSlideController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.vertical_slide;
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
    public void onClick() {
        appRouter.resetTo(DemosController.createScreen());
    }
}
