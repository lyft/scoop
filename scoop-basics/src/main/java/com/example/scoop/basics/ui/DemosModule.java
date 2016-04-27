package com.example.scoop.basics.ui;

import android.app.NotificationManager;
import com.example.scoop.basics.MainActivityModule;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.transitions.TransitionsController;
import dagger.Provides;

@dagger.Module(
        injects = {
                TransitionsController.class,
                DemosController.class
        },
        addsTo = MainActivityModule.class,
        library = true
)
public class DemosModule {

    @Provides
    DemosController provideDemosController(AppRouter appRouter, NotificationManager notificationManager) {
        return new DemosController(appRouter, notificationManager);
    }
}
