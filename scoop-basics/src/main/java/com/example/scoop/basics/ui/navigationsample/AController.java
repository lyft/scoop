package com.example.scoop.basics.ui.navigationsample;

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

@ControllerModule(AController.Module.class)
@Layout(R.layout.a)
public class AController extends FrameLayout {

    public static Screen createScreen() {
        return  Screen.create(AController.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class);
    }

    @dagger.Module(
            injects = AController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module { }

    @Inject
    AppRouter appRouter;

    public AController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @OnClick(R.id.go_to_b_button)
    public void goToB() {
        appRouter.goTo(BController.createScreen());
    }
}
