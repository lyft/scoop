package com.example.scoop.basics;

import android.support.multidex.MultiDexApplication;
import com.example.scoop.basics.scoop.ButterKnifeViewBinder;
import com.facebook.stetho.Stetho;
import com.lyft.scoop.Scoop;
import timber.log.Timber;

public class App extends MultiDexApplication {

    private AppComponent appComponent;

    //private ObjectGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        Timber.plant(new Timber.DebugTree());

        Scoop.setViewBinder(new ButterKnifeViewBinder());

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        //applicationGraph = ObjectGraph.create(new AppModule(this));
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
