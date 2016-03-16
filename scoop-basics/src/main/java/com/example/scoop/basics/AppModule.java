package com.example.scoop.basics;

import android.app.Application;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DialogRouter;
import com.lyft.scoop.dagger.DaggerScreenScooper;
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
    DaggerScreenScooper provideDaggerScreenScooper() {
        return new DaggerScreenScooper();
    }

    @Singleton
    @Provides
    AppRouter provideAppRouter(DaggerScreenScooper daggerScreenScooper) {
        return new AppRouter(daggerScreenScooper, false);
    }

    @Singleton
    @Provides
    DialogRouter provideDialogRouter(DaggerScreenScooper daggerScreenScooper) {
        return new DialogRouter(new AppRouter(daggerScreenScooper, true));
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return app;
    }

}
