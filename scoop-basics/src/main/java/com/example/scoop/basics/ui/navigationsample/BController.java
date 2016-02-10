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

@ControllerModule(BController.Module.class)
@Layout(R.layout.b)
public class BController extends FrameLayout {

    public BController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(BController.class)
                .enterTransition(ForwardSlideTransition.class)
                .exitTransition(BackwardSlideTransition.class);
    }

    @dagger.Module(
            injects = BController.class,
            addsTo = AController.Module.class,
            library = true
    )
    public static class Module {
    }

    @Inject
    AppRouter appRouter;

    @OnClick(R.id.go_back_button)
    public void goBack() {
        appRouter.goBack();
    }

    @OnClick(R.id.go_to_c_button)
    public void goToB() {
        appRouter.goTo(CController.createScreen());
    }
}
