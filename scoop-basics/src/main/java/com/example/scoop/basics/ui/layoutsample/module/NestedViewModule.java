package com.example.scoop.basics.ui.layoutsample.module;

import com.example.scoop.basics.ui.layoutsample.LayoutInjectData;
import com.example.scoop.basics.ui.layoutsample.layout.NestedView;
import dagger.Module;
import dagger.Provides;

@Module(
        injects = NestedView.class,
        addsTo = NestedLayoutModule.class,
        library = true
)
public class NestedViewModule {

    @Provides
    public LayoutInjectData provideInjectData() {
        return new LayoutInjectData("This is injected data.");
    }
}
