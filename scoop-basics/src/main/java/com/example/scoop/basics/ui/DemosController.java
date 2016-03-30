package com.example.scoop.basics.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import butterknife.OnClick;
import com.example.scoop.basics.R;
import com.example.scoop.basics.androidservices.SampleIntentService;
import com.example.scoop.basics.rx.ViewSubscriptions;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.layoutsample.screen.LayoutScreen;
import com.example.scoop.basics.ui.layoutsample.screen.NestedLayoutScreen;
import com.example.scoop.basics.ui.navigationsample.screen.AScreen;
import com.example.scoop.basics.ui.paramsample.screen.ParametrizedScreen;
import com.example.scoop.basics.ui.transitions.TransitionsScreen;
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

    @OnClick(R.id.parametrized_button)
    public void goToParametrizedSample() {
        appRouter.goTo(new ParametrizedScreen("This is passed param"));
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
        appRouter.replaceAllWith(new DemoScreen(), new NestedLayoutScreen());
    }

    @OnClick(R.id.transitions_button)
    public void goToTransitions() {
        appRouter.goTo(new TransitionsScreen());
    }

    @OnClick(R.id.notification_button)
    public void openNotification() {
        PendingIntent contentIntent = PendingIntent.getService(this, 0,
                new Intent(this, SampleIntentService.class), 0);
        Notification notification = new Notification.Builder(getView().getContext())
                .setContentTitle("This is a push notification.")
                .setContentText("Scoop!")
                .build();
    }
}
