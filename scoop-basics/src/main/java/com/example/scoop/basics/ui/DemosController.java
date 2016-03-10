package com.example.scoop.basics.ui;

import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.customtransition.screen.AutoTransitionStartScreen;
import com.example.scoop.basics.ui.layoutsample.screen.LayoutScreen;
import com.example.scoop.basics.ui.layoutsample.screen.NestedLayoutScreen;
import com.example.scoop.basics.ui.navigationsample.screen.AScreen;
import com.example.scoop.basics.ui.paramsample.screen.ParametrizedScreen;
import com.example.scoop.basics.ui.standardtransitions.screen.FadeScreen;
import com.example.scoop.basics.ui.wizardsample.screen.EnterFirstNameScreen;
import com.lyft.scoop.ViewController;
import javax.inject.Inject;

public class DemosController extends ViewController {

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
    public void onAttach() {
        super.onAttach();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        viewSubscriptions.unsubscribe();
    }

    @OnClick(R.id.navigation_sample_button)
    public void goToNavigationSample() {
        appRouter.goTo(new AScreen());
    }

    @OnClick(R.id.custom_transition_button)
    public void goToCustomTransitionSample() {
        appRouter.goTo(new AutoTransitionStartScreen());
    }

    @OnClick(R.id.parametrized_button)
    public void goToParametrizedSample() {
        appRouter.goTo(new ParametrizedScreen("This is passed param"));
    }

    @OnClick(R.id.standard_transitions_button)
    public void goToStandardTransitions() {
        appRouter.goTo(new FadeScreen());
    }

    @OnClick(R.id.wizard_sample_button)
    public void goToWizardSample() {
        appRouter.goTo(new EnterFirstNameScreen());
    }

    @OnClick(R.id.layout_sample_button)
    public void goToLayoutSample() {
        appRouter.goTo(new LayoutScreen("This is a parameter."));
    }

    @OnClick(R.id.nested_layout_sample_button)
    public void goToNestedLayoutSample() {
        appRouter.goTo(new NestedLayoutScreen());
    }
}
