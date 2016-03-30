package com.example.scoop.basics.androidservices;

import android.app.IntentService;
import android.content.Intent;
import com.example.scoop.basics.App;
import com.example.scoop.basics.MainActivity;
import com.example.scoop.basics.scoop.AppRouter;
import com.example.scoop.basics.ui.navigationsample.screen.AScreen;
import javax.inject.Inject;
import timber.log.Timber;

public class SampleIntentService extends IntentService {

    @Inject
    AppRouter appRouter;

    public SampleIntentService() {
        super(SampleIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.d("onCreate");
        getApp().getApplicationGraph().inject(this);
    }

    private App getApp() {return (App) this.getApplicationContext();}

    @Override
    protected void onHandleIntent(Intent intent) {
        appRouter.goTo(new AScreen());
        Intent mainActivityIntent = new Intent(this, MainActivity.class);
        mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(mainActivityIntent);
    }
}
