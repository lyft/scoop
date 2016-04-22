package com.example.scoop.basics.ui;

import android.app.NotificationManager;
import android.util.Log;
import com.example.scoop.basics.MainActivityModule;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.transitions.TransitionsController;
import dagger.Provides;

@dagger.Module(
        injects = {
                DemosController.class,
                TransitionsController.class
        },
        addsTo = MainActivityModule.class,
        library = true
)
public class DemosModule {

    @Provides
    DemosController provideDemosController(AppRouter appRouter, NotificationManager notificationManager) {
        Log.i("DemosModule", "provideDemosController");
        return new DemosController(appRouter, notificationManager);
    }
}
