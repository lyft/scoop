package com.example.scoop.basics.ui;

import android.view.View;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.InjectThat;
import com.example.scoop.basics.ui.customtransition.AutoTransitionStartController;
import com.example.scoop.basics.ui.navigationsample.AController;
import com.example.scoop.basics.ui.paramsample.ParametrizedController;
import com.example.scoop.basics.ui.standardtransitions.FadeController;
import com.example.scoop.basics.ui.wizardsample.EnterFirstNameController;
import com.lyft.scoop.Screen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

@InjectThat(DemosControllerInjector.class)
public class DemosController extends ViewController {

    public static Screen createScreen() {
        return Screen.create(DemosController.class);
    }

    @dagger.Module(
    )
    public static class Module {}

    private AppRouter appRouter;

    ViewSubscriptions viewSubscriptions = new ViewSubscriptions();

    @Inject
    public DemosController(AppRouter appRouter) {
        this.appRouter = appRouter;
    }

    @Override
    protected int layoutId() {
        return R.layout.demos;
    }

    @Override
    public void attach(View view) {
        super.attach(view);
    }

    @Override
    public void detach(View view) {
        super.detach(view);

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
