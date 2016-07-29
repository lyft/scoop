package com.example.scoop.basics;

import android.support.multidex.MultiDexApplication;
import com.facebook.stetho.Stetho;
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

        applicationGraph = ObjectGraph.create(new AppModule(this));
    }

    public ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }
}
