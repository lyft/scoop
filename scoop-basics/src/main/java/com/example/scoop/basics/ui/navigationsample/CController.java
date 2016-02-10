package com.example.scoop.basics.ui.navigationsample;

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

@ControllerModule(CController.Module.class)
@Layout(R.layout.c)
public class CController extends FrameLayout {

    public CController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(CController.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(BackwardSlideTransition.class);
    }

    @dagger.Module(
            injects = CController.class,
            addsTo = BController.Module.class,
            library = true
    )
    public static class Module { }

    @Inject
    AppRouter appRouter;

    @OnClick(R.id.reset_to_a_button)
    public void onClick() {
        appRouter.resetTo(AController.createScreen());
    }
}
