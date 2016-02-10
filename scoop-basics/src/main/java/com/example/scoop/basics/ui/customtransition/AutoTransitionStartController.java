package com.example.scoop.basics.ui.customtransition;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.DemosController;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.FadeTransition;
import javax.inject.Inject;

@ControllerModule(AutoTransitionStartController.Module.class)
@Layout(R.layout.auto_transition_start)
public class AutoTransitionStartController extends LinearLayout {

    public AutoTransitionStartController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(AutoTransitionStartController.class)
                .enterTransition(FadeTransition.class)
                .exitTransition(FadeTransition.class);
    }

    @dagger.Module(
            injects = AutoTransitionStartController.class,
            addsTo = DemosController.Module.class,
            library = true
    )
    public static class Module { }

    @Inject
    AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        viewSubscriptions.unsubscribe();
    }

    @OnClick(R.id.next_button)
    public void goNext() {
        appRouter.goTo(AutoTransitionEndController.createScreen());
    }
}
