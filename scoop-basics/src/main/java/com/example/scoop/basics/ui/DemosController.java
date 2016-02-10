package com.example.scoop.basics.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import butterknife.OnClick;
import com.example.scoop.basics.MainActivityModule;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.ControllerModule;
import com.example.scoop.basics.ui.customtransition.AutoTransitionStartController;
import com.example.scoop.basics.ui.navigationsample.AController;
import com.example.scoop.basics.ui.paramsample.ParametrizedController;
import com.example.scoop.basics.ui.standardtransitions.FadeController;
import com.example.scoop.basics.ui.wizardsample.EnterFirstNameController;
import com.lyft.scoop.Layout;
import com.lyft.scoop.Screen;
import javax.inject.Inject;

@ControllerModule(DemosController.Module.class)
@Layout(R.layout.demos)
public class DemosController extends LinearLayout {

    @dagger.Module(
            injects = DemosController.class,
            addsTo = MainActivityModule.class,
            library = true
    )
    public static class Module {}

    @Inject
    AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    public DemosController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static Screen createScreen() {
        return Screen.create(DemosController.class);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        viewSubscriptions.unsubscribe();
    }

    @OnClick(R.id.navigation_sample_button)
    public void goToNavigationSample() {
        appRouter.goTo(AController.createScreen());
    }

    @OnClick(R.id.custom_transition_button)
    public void goToCustomTransitionSample() {
        appRouter.goTo(AutoTransitionStartController.createScreen());
    }

    @OnClick(R.id.parametrized_button)
    public void goToParametrizedSample() {
        appRouter.goTo(ParametrizedController.createScreen("This is passed param"));
    }

    @OnClick(R.id.standard_transitions_button)
    public void goToStandardTransitions() {
        appRouter.goTo(FadeController.createScreen());
    }

    @OnClick(R.id.wizard_sample_button)
    public void goToWizardSample() {
        appRouter.goTo(EnterFirstNameController.createScreen());
    }
}
