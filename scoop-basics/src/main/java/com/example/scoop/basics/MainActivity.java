package com.example.scoop.basics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DialogRouter;
import com.example.scoop.basics.scoop.DialogUiContainer;
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

    @Bind(R.id.dialog_container)
    DialogUiContainer dialogContainer;

    @Inject
    AppRouter appRouter;

    @Inject
    DialogRouter dialogRouter;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private Scoop rootScoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getRootScoop().inflate(R.layout.root, (ViewGroup) findViewById(R.id.root), true);

        ButterKnife.bind(this);

        DaggerInjector.fromScoop(getRootScoop()).inject(this);

        appRouter.onCreate(rootScoop);
        dialogRouter.onCreate(rootScoop);
        appRouter.goTo(new DemoScreen());

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

        if (dialogContainer.onBack()) {
            return;
        }

        if (mainUiContainer.onBack()) {
            return;
        }

        if (dialogRouter.dismiss()) {
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
