package com.example.scoop.basics.androidservices;

import android.app.IntentService;
import android.content.Intent;
import com.example.scoop.basics.MainActivity;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.navigationsample.screen.AScreen;
import com.lyft.scoop.dagger.DaggerInjector;
import javax.inject.Inject;

public class SampleIntentService extends IntentService {

    @Inject
    AppRouter appRouter;

    public SampleIntentService() {
        super(SampleIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerInjector.fromContext(this.getApplicationContext()).inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        appRouter.goTo(new AScreen());
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(mainActivityIntent);
    }
}
