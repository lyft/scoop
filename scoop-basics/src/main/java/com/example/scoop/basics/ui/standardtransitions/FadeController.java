package com.example.scoop.basics.ui.standardtransitions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

@ControllerModule(FadeController.Module.class)
@Layout(R.layout.fade)
public class FadeController extends FrameLayout {

    public FadeController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return  Screen.create(FadeController.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class);
    }

    @dagger.Module(
            injects = FadeController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module { }

    @Inject
    AppRouter appRouter;

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(HorizontalSlideController.createScreen());
    }
}
