package com.example.scoop.basics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.MainUiContainer;
import com.example.scoop.basics.ui.DemoScreen;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.dagger.DaggerInjector;
import dagger.ObjectGraph;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.screen_container)
    MainUiContainer mainUiContainer;

    @Inject
    AppRouter appRouter;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private Scoop activityScoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_main);

        getActivityScoop().inflate(R.layout.root, (ViewGroup) findViewById(R.id.root), true);

        ButterKnife.bind(this);

        DaggerInjector.fromScoop(getActivityScoop()).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");

        if (!appRouter.hasActiveScreen()) {
            appRouter.goTo(new DemoScreen());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        Timber.d("onDestroy");
        subscriptions.clear();
        getActivityScoop().destroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onBackPressed() {

        if (mainUiContainer.onBack()) {
            return;
        }

        if (appRouter.goBack()) {
            return;
        }

        super.onBackPressed();
    }

    private Scoop getActivityScoop() {
        if (activityScoop == null) {
            Timber.d("getActivityScoop");
            ObjectGraph activityGraph = getApp().getApplicationGraph().plus(new MainActivityModule(this));

            DaggerInjector activityInjector = new DaggerInjector(activityGraph);

            activityScoop = new Scoop.Builder("activity_scoop")
                    .service(DaggerInjector.SERVICE_NAME, activityInjector)
                    .build();
        }

        return activityScoop;
    }

    private App getApp() {
        return (App) getApplicationContext();
    }
}
