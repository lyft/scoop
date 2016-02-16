package com.example.scoop.basics.ui.layoutsample.module;

import com.example.scoop.basics.ui.DemosModule;
import com.example.scoop.basics.ui.layoutsample.LayoutInjectData;
import com.example.scoop.basics.ui.layoutsample.layout.LayoutView;
import dagger.Module;
import dagger.Provides;

@Module(
        injects = LayoutView.class,
        addsTo = DemosModule.class,
        library = true
)
public class LayoutModule {
    @Provides
    public LayoutInjectData provideInjectData() {
        return new LayoutInjectData("This is injected data.");
    }
}

