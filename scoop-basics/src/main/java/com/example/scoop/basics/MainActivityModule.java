package com.example.scoop.basics;

import android.app.Activity;
import dagger.Module;
import dagger.Provides;

@Module()
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    Activity provideActivity() {
        return mainActivity;
    }
}
