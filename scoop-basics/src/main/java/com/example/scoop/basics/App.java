package com.example.scoop.basics;

import android.support.multidex.MultiDexApplication;
import com.example.scoop.basics.scoop.ButterKnifeViewBinder;
import com.facebook.stetho.Stetho;
import com.lyft.scoop.Scoop;
import com.lyft.scoop.ViewBinder;
import com.lyft.scoop.ViewBinderFactory;
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

        Scoop.setViewBinderFactory(VIEW_BINDER_FACTORY);

        applicationGraph = ObjectGraph.create(new AppModule(this));
    }

    public ObjectGraph getApplicationGraph() {
        return applicationGraph;
    }

    private static final ViewBinderFactory VIEW_BINDER_FACTORY = new ViewBinderFactory() {
        @Override
        public ViewBinder create(Object object) {
            return new ButterKnifeViewBinder();
        }
    };
}
