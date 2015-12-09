package com.example.scoop.basics;

import android.app.Application;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DaggerScreenScooper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module(
        injects = {
                App.class,
        },
        includes = {
        },
        library = true
)
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    AppRouter provideAppRouter() {
        return new AppRouter(new DaggerScreenScooper());
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return app;
    }

}
