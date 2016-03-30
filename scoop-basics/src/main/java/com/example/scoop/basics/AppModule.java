package com.example.scoop.basics;

import android.app.Application;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.scoop.DialogRouter;
import com.lyft.scoop.ScreenScoopFactory;
import com.lyft.scoop.ScreenScooper;
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

    @Provides
    ScreenScooper provideDaggerScreenScooper() {
        return new DaggerScreenScooper();
    }

    @Provides
    ScreenScoopFactory provideScreenFactory(ScreenScooper screenScooper) {
        return new ScreenScoopFactory(screenScooper);
    }

    @Singleton
    @Provides
    AppRouter provideAppRouter() {
        return new AppRouter(false);
    }

    @Singleton
    @Provides
    DialogRouter provideDialogRouter() {
        return new DialogRouter(new AppRouter(true));
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return app;
    }
}
