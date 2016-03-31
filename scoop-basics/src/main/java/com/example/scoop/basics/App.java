package com.example.scoop.basics;

import android.support.multidex.MultiDexApplication;
import com.example.scoop.basics.scoop.ButterKnifeViewBinder;
import com.facebook.stetho.Stetho;
import com.lyft.scoop.Scoop;
import dagger.ObjectGraph;
import timber.log.Timber;

public class App extends MultiDexApplication {

    private ObjectGraph applicationGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        Timber.plant(new Timber.DebugTree());

        Timber.d("onCreate");

        Scoop.setViewBinder(new ButterKnifeViewBinder());

        applicationGraph = ObjectGraph.create(new AppModule(this));
    }

    public ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }
}
