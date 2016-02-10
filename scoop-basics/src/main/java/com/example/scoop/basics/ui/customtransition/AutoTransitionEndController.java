package com.example.scoop.basics.ui.customtransition;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import javax.inject.Inject;

@ControllerModule(AutoTransitionEndController.Module.class)
@Layout(R.layout.auto_transition_end)
public class AutoTransitionEndController extends LinearLayout {

    public AutoTransitionEndController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(AutoTransitionEndController.class)
                .enterTransition(AutoTransition.class)
                .exitTransition(AutoTransition.class);

    }

    @dagger.Module(
            injects = AutoTransitionEndController.class,
            addsTo = AutoTransitionStartController.Module.class,
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

    @OnClick(R.id.back_button)
    public void goNext() {
        appRouter.goBack();
    }
}
