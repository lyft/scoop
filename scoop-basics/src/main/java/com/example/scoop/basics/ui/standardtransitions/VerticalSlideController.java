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
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;
import javax.inject.Inject;

@ControllerModule(VerticalSlideController.Module.class)
@Layout(R.layout.vertical_slide)
public class VerticalSlideController extends FrameLayout {

    public VerticalSlideController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(VerticalSlideController.class)
                .enterTransition(UpwardSlideTransition.class)
                .exitTransition(DownwardSlideTransition.class);
    }

    @dagger.Module(
            injects = VerticalSlideController.class,
            addsTo = HorizontalSlideController.Module.class,
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
    public void onClick() {
        appRouter.resetTo(DemosController.createScreen());
    }
}
