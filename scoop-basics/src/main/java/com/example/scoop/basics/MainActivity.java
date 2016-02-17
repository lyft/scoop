package com.example.scoop.basics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.DemoScreen;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.UiContainer;
import com.lyft.scoop.dagger.DaggerInjector;
import dagger.ObjectGraph;
import javax.inject.Inject;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.screen_container)
    UiContainer mainUiContainer;

    @Inject
    AppRouter appRouter;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private LayoutInflater inflater;
    private Scoop rootScoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRootScoop().inflate(R.layout.root, (ViewGroup) findViewById(R.id.root), true);

        ButterKnife.bind(this);

        DaggerInjector.fromScoop(getRootScoop()).inject(this);

        appRouter.onCreate(rootScoop, new DemoScreen());

        Timber.d("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {

        Timber.d("onDestroy");

        subscriptions.clear();

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

    private Scoop getRootScoop() {
        if (rootScoop == null) {
            ObjectGraph activityGraph = getApp().getApplicationGraph().plus(new MainActivityModule(this));

            DaggerInjector activityInjector = new DaggerInjector(activityGraph);

            rootScoop = new Scoop.Builder("root")
                    .service(DaggerInjector.SERVICE_NAME, activityInjector)
                    .build();
        }

        return rootScoop;
    }

    private App getApp() {
        return (App) getApplicationContext();
    }
}
