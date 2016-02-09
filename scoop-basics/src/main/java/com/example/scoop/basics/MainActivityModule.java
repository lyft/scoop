package com.example.scoop.basics;

import android.app.Activity;
import com.example.scoop.basics.scoop.MainUiContainer;
import com.example.scoop.basics.ui.layoutsample.LayoutView;
import dagger.Provides;

@dagger.Module(
        injects = {
                MainActivity.class,
                MainUiContainer.class,
                LayoutView.class
        },
        addsTo = AppModule.class,
        includes = {
        },
        library = true
)
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
