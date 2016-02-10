package com.example.scoop.basics.ui.standardtransitions;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;
import javax.inject.Inject;

@ControllerModule(HorizontalSlideController.Module.class)
@Layout(R.layout.horizontal_slide)
public class HorizontalSlideController extends FrameLayout {

    public HorizontalSlideController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(HorizontalSlideController.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(BackwardSlideTransition.class);
    }

    @dagger.Module(
            injects = HorizontalSlideController.class,
            addsTo = FadeController.Module.class,
            library = true
    )
    public static class Module {
    }

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
        appRouter.goTo(VerticalSlideController.createScreen());
    }
}
